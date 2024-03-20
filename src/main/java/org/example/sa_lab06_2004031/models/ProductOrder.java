package org.example.sa_lab06_2004031.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder implements Serializable{
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
    private String customerId;
    private int quantity;
    private double price;
    private String status;
    private String address;
}
