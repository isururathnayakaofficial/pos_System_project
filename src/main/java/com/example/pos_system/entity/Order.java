package com.example.pos_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter

@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
private String orderId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
private Date date;
private BigDecimal amount;
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;





    public Order(String orderId, String itemId, Customer customer, Date date, BigDecimal amount) {
        this.orderId = orderId;
        this.customer = customer;
        this.date = date;
        this.amount = amount;

    }
}
