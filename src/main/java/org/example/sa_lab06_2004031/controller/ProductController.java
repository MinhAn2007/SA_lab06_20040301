package org.example.sa_lab06_2004031.controller;

import org.example.sa_lab06_2004031.dto.OrderForm;
import org.example.sa_lab06_2004031.models.Product;
import org.example.sa_lab06_2004031.services.ProductService;
import org.example.sa_lab06_2004031.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addOrder")
    public String placeOrder(@ModelAttribute("orderForm") OrderForm orderForm, Model model) {
        System.out.println(orderForm.getName());
       String orderJson = orderForm.getName() + " " + orderForm.getEmail() + " " + orderForm.getAddress() + " " + orderForm.getPhone() + " " + orderForm.getNote() + " " + orderForm.getProductId() + " " + orderForm.getQuantity() + " " + orderForm.getOrderId();
       System.out.println(orderJson);
        return "orderSuccess";
    }


}
