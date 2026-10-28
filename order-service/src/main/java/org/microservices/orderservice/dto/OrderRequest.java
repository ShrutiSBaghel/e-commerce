package org.microservices.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private List<OrderLineItemDto> orderLineItemDtoList;
}
