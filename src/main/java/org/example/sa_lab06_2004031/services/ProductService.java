package org.example.sa_lab06_2004031.services;

import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.example.sa_lab06_2004031.models.Product;
import org.example.sa_lab06_2004031.repositories.ProductOrderRepository;
import org.example.sa_lab06_2004031.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @JmsListener(destination = "order_products")
    public void processOrder(final Message jsonMessage) throws Exception {
        String messageData = null;
        String response = null;
        if(jsonMessage instanceof TextMessage) {
            //1. read message data
            String encodeJson = ((TextMessage) jsonMessage).getText();
            //2. ==> decode
//            List<Product> products=EncodingUtils.decode(encodeJson);
            //3. check for quantity
//            String result = makeOrder(products);
            //4. make order or reject
//            MailingService.sendTextMail("@gmail.com",result);
            //5. send email
        }

    }

    private String makeOrder(List<Product> products) {
        return null;
    }
}
