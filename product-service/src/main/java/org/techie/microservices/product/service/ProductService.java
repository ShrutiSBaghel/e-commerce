package org.techie.microservices.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techie.microservices.product.dto.ProductDto;
import org.techie.microservices.product.model.Product;
import org.techie.microservices.product.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is created", product.getId());
    }

    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        products.forEach(product -> productDtos.add(ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                .build()));
        return productDtos;

        //can also use map for the same, same as forEach and then just return it as toList(),
        //this way we won't have to create a new ProductDto list.
    }
}
