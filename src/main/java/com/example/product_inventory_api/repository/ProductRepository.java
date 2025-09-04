package com.example.product_inventory_api.repository;

import com.example.product_inventory_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // This annotation is optional but good practice. It indicates that the interface is a Repository component.
public interface ProductRepository extends JpaRepository<Product, Long> {
    // That's it! No need to write any method implementations.
    // JpaRepository provides all CRUD methods (save, findById, findAll, deleteById, etc.)
}