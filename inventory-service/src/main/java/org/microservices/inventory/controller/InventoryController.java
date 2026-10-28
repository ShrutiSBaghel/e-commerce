package org.microservices.inventory.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.microservices.inventory.dto.InventoryResponse;
import org.microservices.inventory.service.InventoryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCodes) {
        return inventoryService.isInStock(skuCodes);
    }
}
