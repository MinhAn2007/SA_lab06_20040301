package org.example.sa_lab06_2004031.services;
import org.example.sa_lab06_2004031.utils.Base64EncodingText;
import org.example.sa_lab06_2004031.utils.CONSTANTS;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.example.sa_lab06_2004031.models.Product;
import org.example.sa_lab06_2004031.repositories.ProductOrderRepository;
import org.example.sa_lab06_2004031.repositories.ProductRepository;
import org.example.sa_lab06_2004031.utils.EncodingText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private Base64EncodingText base64EncodingText;

    @Autowired
    private ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public Product save(Product product) {
        return productRepository.save(product);
    }
    @JmsListener(destination = "order_products")
    public void processOrder(final Message jsonMessage) throws Exception {
        if(jsonMessage instanceof TextMessage) {
            //1. read message data
            String encodeJson = ((TextMessage) jsonMessage).getText();
            //2. decode
            ObjectMapper objectMapper = new ObjectMapper();
            List<Product> products = objectMapper.readValue(base64EncodingText.decrypt(encodeJson), new TypeReference<List<Product>>(){});
            //3. check for quantity
            String result = makeOrder(products);
            //4. make order or reject
            MailingService.sendTextMail(CONSTANTS.ORDER_INFORMATION_EMAIL, base64EncodingText.encrypt(result));
            //5. send email
        }
    }

    private String makeOrder(List<Product> products) {
        for (Product product : products) {
            Optional<Product> productOptional = productRepository.findById(product.getId());
            if (productOptional.isPresent()) {
                Product existingProduct = productOptional.get();
                if (existingProduct.getStock() < product.getStock()) {
                    return "Order rejected due to insufficient stock for product: " + existingProduct.getName();
                }
                existingProduct.setStock(existingProduct.getStock() - product.getStock());
                productRepository.save(existingProduct);
            } else {
                return "Order rejected due to product not found: " + product.getName();
            }
        }
        return "Order confirmed";
    }
    public List<Product> findProductsByIds(List<Long> ids) {
        List<Product> productList = new ArrayList<>();
        for (Long id : ids) {
            Optional<Product> productOptional = productRepository.findById(id);
            productOptional.ifPresent(productList::add);
        }
        return productList;
    }


}
