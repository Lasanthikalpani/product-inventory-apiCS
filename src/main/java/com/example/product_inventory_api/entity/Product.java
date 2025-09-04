package com.example.product_inventory_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Tells Spring JPA that this class is a model object representing a database table.
@Table(name = "products") // Explicitly specifies the name of the table in the database.
@Data // Lombok annotation: Generates getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor // Lombok annotation: Generates a no-argument constructor.
@AllArgsConstructor // Lombok annotation: Generates a constructor with all arguments.
public class Product {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way of incrementing the primary key. IDENTITY uses the database's auto-increment (e.g., MySQL AUTO_INCREMENT).
    private Long id;

    @Column(name = "name", nullable = false) // Maps the field to the 'name' column and specifies it cannot be null.
    private String name;

    @Column(name = "description") // Maps the field to the 'description' column. Nullable by default.
    private String description;

    @Column(name = "price", nullable = false) // Maps the field to the 'price' column and specifies it cannot be null.
    private Double price;

    @Column(name = "quantity", nullable = false) // Maps the field to the 'quantity' column and specifies it cannot be null.
    private Integer quantity;
}