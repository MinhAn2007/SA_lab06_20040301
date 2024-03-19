package org.example.sa_lab06_2004031.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String note;
    private Long productId;
    private Integer quantity;
    private Long orderId;
}
