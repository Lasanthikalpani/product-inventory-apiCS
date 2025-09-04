package com.example.product_inventory_api.service.impl;

import com.example.product_inventory_api.entity.Product;
import com.example.product_inventory_api.repository.ProductRepository;
import com.example.product_inventory_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Marks this class as a Spring Service component
public class ProductServiceImpl implements ProductService {

    @Autowired // Injects the ProductRepository dependency
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        // JpaRepository's findAll() returns a List<Product>
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        // findById returns an Optional<Product>.
        // We use orElseThrow to throw an exception if the product is not found.
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public Product createProduct(Product product) {
        // JpaRepository's save method is used for both create and update.
        // It returns the saved entity (with its generated ID).
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        // 1. Find the existing product
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // 2. Update the existing product's fields with the new details
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setQuantity(productDetails.getQuantity());

        // 3. Save the updated product back to the database
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        // 1. Check if the product exists before deleting
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // 2. If it exists, delete it
        productRepository.delete(product);
    }
}