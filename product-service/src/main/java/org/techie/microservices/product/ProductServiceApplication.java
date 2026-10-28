package org.techie.microservices.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.techie.microservices.product.repository.ProductRepository;

@SpringBootApplication
@Slf4j
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);

        log.info("Application is running");
    }

}
