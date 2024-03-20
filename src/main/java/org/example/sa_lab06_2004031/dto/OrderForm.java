package org.example.sa_lab06_2004031.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {
    private String productName;
    private int quantity;
    private String customerName;
    private String address;
    private String paymentMethod;
    private String notes;
    private List<Long> selectedIds;

}
