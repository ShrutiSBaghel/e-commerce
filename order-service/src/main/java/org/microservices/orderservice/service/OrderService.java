package org.microservices.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservices.orderservice.config.WebClientConfig;
import org.microservices.orderservice.dto.InventoryResponse;
import org.microservices.orderservice.dto.OrderLineItemDto;
import org.microservices.orderservice.dto.OrderRequest;
import org.microservices.orderservice.model.Order;
import org.microservices.orderservice.model.OrderLineItem;
import org.microservices.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItemList = orderRequest.getOrderLineItemDtoList()
                .stream().map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItemList);

        //save it all only when all product is in stock, that we will know from inventory
        List<String> skuCodes = new ArrayList<>();
        orderLineItemList.forEach(item -> skuCodes.add(item.getSkuCode()));
        InventoryResponse[] inventoryResponses =webClientBuilder.build().get()
                        .uri("http://inventory-service/api/inventory",
                                uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes)
                                        .build())
                                .retrieve()
                                        .bodyToMono(InventoryResponse[].class)
                                                .block();

        boolean allProductInStock = inventoryResponses != null
                && inventoryResponses.length > 0 &&
        Arrays.stream(inventoryResponses)
                        .allMatch(InventoryResponse::getIsInStock);

        if(allProductInStock) {
            orderRepository.save(order);
            log.info("Order {} saved successfully", order.getOrderNumber());
        } else {
            throw new IllegalArgumentException("Product is not in stock, try again later");
        }
    }

    public OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
        return OrderLineItem.builder()
                .id(orderLineItemDto.getId())
                .skuCode(orderLineItemDto.getSkuCode())
                .price(orderLineItemDto.getPrice())
                .quantity(orderLineItemDto.getQuantity())
                .build();
    }
}
