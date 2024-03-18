package org.example.sa_lab06_2004031.repositories;

import org.example.sa_lab06_2004031.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}