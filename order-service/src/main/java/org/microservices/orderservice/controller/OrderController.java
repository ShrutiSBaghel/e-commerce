package org.microservices.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.microservices.orderservice.dto.OrderRequest;
import org.microservices.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {

    //lombok generated constructos for field with 'final' keyword. w/o this you will get null pointer
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }
}
