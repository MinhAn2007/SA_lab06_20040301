package org.example.sa_lab06_2004031.repositories;

import org.example.sa_lab06_2004031.models.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}