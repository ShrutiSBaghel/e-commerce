package org.microservices.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.microservices.inventory.model.Inventory;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {


    //Optional -> a container object, to represent a value that may or may not be present
    // introduced in Java 8, only use for return type of method (not as field or parameter
    // data type)
    Optional<Inventory> findBySkuCode(String skuCode);

    // JPA Queries
    List<Inventory> findBySkuCodeIn(List<String> skuCodes);




}
