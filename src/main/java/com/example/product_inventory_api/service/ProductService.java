package com.example.product_inventory_api.service;

import com.example.product_inventory_api.entity.Product;
import java.util.List;

public interface ProductService {
    // Get all products
    List<Product> getAllProducts();

    // Get a single product by its ID
    Product getProductById(Long id);

    // Create a new product
    Product createProduct(Product product);

    // Update an existing product
    Product updateProduct(Long id, Product productDetails);

    // Delete a product
    void deleteProduct(Long id);
}