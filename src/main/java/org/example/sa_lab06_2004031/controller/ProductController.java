package org.example.sa_lab06_2004031.controller;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.sa_lab06_2004031.dto.OrderForm;
import org.example.sa_lab06_2004031.models.Product;
import org.example.sa_lab06_2004031.models.ProductOrder;
import org.example.sa_lab06_2004031.services.ProductOrderService;
import org.example.sa_lab06_2004031.services.ProductService;
import org.example.sa_lab06_2004031.utils.Base64EncodingText;
import org.example.sa_lab06_2004031.utils.JsonUtils;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductOrderService productOrderService;
    @GetMapping("/orderForm")
    public String showOrderForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Long> selectedIds = (List<Long>) session.getAttribute("selectedIds");
        System.out.println(selectedIds);
        if (selectedIds != null) {
            List<Product> productList = productService.findProductsByIds(selectedIds);
            System.out.println(productList.get(0).getName());
            model.addAttribute("productList", productList);
        }

        model.addAttribute("orderForm", new OrderForm());
        return "orderForm";
    }
    @GetMapping("/products")
    public String showProductList(Model model, HttpSession session) {
        if (session.getAttribute("products") == null) {
            List<Product> products = productService.findAll();
            session.setAttribute("products", products);
        }
        List<Product> productList = (List<Product>) session.getAttribute("products");
        model.addAttribute("products", productList);
        System.out.println(productList);
        return "productList";
    }

    @PostMapping("/addOrder")
    public String processOrder(@ModelAttribute OrderForm orderForm, Model model, HttpSession session) {
        List<Long> selectedIds = orderForm.getSelectedIds();
        List<Product> productList = productService.findProductsByIds(selectedIds);
        for (Product product : productList) {
            for (Long productId : selectedIds) {
                if (product.getId().equals(productId)) {
                    int quantityOrdered = orderForm.getQuantity();
                    int stock = product.getStock();
                    if (quantityOrdered > stock) {
                        model.addAttribute("errorMessage", "Số lượng đặt hàng vượt quá số lượng tồn kho cho sản phẩm " + product.getName());
                        session.invalidate();
                        return "redirect:/product/products";
                    } else {
                        product.setStock(stock - quantityOrdered);
                        productService.save(product);

                        ProductOrder productOrder = new ProductOrder();
                        productOrder.setProduct(product);
                        productOrder.setCustomerId(orderForm.getCustomerName());
                        productOrder.setQuantity(quantityOrdered);
                        productOrder.setPrice(product.getPrice());
                        productOrder.setStatus("Đã đặt hàng");
                        productOrder.setAddress(orderForm.getAddress());
                        productOrderService.save(productOrder);
                    }
                }
            }
        }
        return "orderSuccess";
    }


    @PostMapping("/submitSelectedProducts")
    public String submitSelectedProducts(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String[] selectedIdsArray = request.getParameterValues("selectedIds");
        List<Long> selectedIds = new ArrayList<>();
        if (selectedIdsArray != null) {
            for (String idString : selectedIdsArray) {
                selectedIds.add(Long.parseLong(idString));
            }
        }
        session.setAttribute("selectedIds", selectedIds);
        System.out.println(selectedIds);
        return "redirect:/product/orderForm";
    }
}
