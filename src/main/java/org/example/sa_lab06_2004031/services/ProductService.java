package org.example.sa_lab06_2004031.services;

import org.example.sa_lab06_2004031.models.Product;
import org.example.sa_lab06_2004031.repositories.ProductOrderRepository;
import org.example.sa_lab06_2004031.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
