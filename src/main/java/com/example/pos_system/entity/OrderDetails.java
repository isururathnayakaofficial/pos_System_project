package com.example.pos_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor

public class OrderDetails {

    @EmbeddedId
    private OrderDetailsPK id;

    @ManyToOne
    @MapsId("orderId") // maps orderId of composite key
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("itemId") // maps itemId of composite key
    @JoinColumn(name = "item_id")
    private Item item;

    private int quantity;
    private BigDecimal unitPrice;

    public OrderDetails() {}

    public OrderDetails(Order order, Item item, int quantity, BigDecimal unitPrice) {
        this.order = order;
        this.item = item;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.id = new OrderDetailsPK(order.getOrderId(), item.getItemId());
    }
}
