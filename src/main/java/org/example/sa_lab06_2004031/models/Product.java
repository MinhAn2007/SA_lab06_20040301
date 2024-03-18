package org.example.sa_lab06_2004031.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductOrder> productOrder;
}
