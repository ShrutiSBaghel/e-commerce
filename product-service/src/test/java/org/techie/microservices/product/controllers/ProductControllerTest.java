package org.techie.microservices.product.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.techie.microservices.product.dto.ProductDto;
import org.techie.microservices.product.repository.ProductRepository;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import tools.jackson.databind.ObjectMapper;
import java.math.BigDecimal;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ProductRepository productRepository;
    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(DockerImageName
            .parse("postgres:16-alpine"));

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.postgres.uri", postgreSQLContainer::getJdbcUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductDto productDto = getProductDto();
        String productDtoString = objectMapper.writeValueAsString(productDto);

        //added this because one new row was getting added everytime I ran the test.
        productRepository.deleteAll();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productDtoString))
                .andExpect(status().isCreated());

        Assertions.assertEquals(1, productRepository.findAll().size());
    }

    public ProductDto getProductDto() {
        return ProductDto.builder()
                .name("iPhone 18")
                .description("latest launch")
                .price(BigDecimal.valueOf(100000))
                .build();
    }


}
