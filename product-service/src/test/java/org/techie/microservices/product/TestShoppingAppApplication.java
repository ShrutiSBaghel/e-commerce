package org.techie.microservices.product;

import org.springframework.boot.SpringApplication;

public class TestShoppingAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(ProductServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
