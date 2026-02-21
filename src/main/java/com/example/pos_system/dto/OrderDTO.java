package com.example.pos_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String orderId;
    private String itemId;
    private String customerId;
    private Date date;
    private BigDecimal amount;



    public OrderDTO(String orderId, String cid, BigDecimal amount, Date date) {
        this.orderId = orderId;
        this.customerId = cid; // store customer id in customerId (was incorrectly assigned to itemId)
        this.amount = amount;
        this.date = date;
    }
}
