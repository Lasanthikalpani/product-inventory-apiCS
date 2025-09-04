package com.example.product_inventory_api.controller;

import com.example.product_inventory_api.entity.Product;
import com.example.product_inventory_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // This annotation marks the class as a REST controller whose methods return JSON/XML responses directly.
@RequestMapping("/api/products") // This defines the base URL for all endpoints in this controller.
public class ProductController {

    @Autowired // Injects the ProductService dependency
    private ProductService productService;

    // GET: Get all products
    // Endpoint: GET http://localhost:8080/api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK); // HTTP 200 OK
    }

    // GET: Get a single product by ID
    // Endpoint: GET http://localhost:8080/api/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK); // HTTP 200 OK
    }

    // POST: Create a new product
    // Endpoint: POST http://localhost:8080/api/products
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED); // HTTP 201 Created
    }

    // PUT: Update an existing product
    // Endpoint: PUT http://localhost:8080/api/products/1
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK); // HTTP 200 OK
    }

    // DELETE: Delete a product
    // Endpoint: DELETE http://localhost:8080/api/products/1
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // HTTP 204 No Content
    }
}