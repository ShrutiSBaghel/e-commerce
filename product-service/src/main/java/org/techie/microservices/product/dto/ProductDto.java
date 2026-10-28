package org.techie.microservices.product.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {

    @Nullable
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
