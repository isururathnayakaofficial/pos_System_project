package com.example.pos_system.entity;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailsPK implements Serializable {

    private String orderId;
    private String itemId;

    public OrderDetailsPK() {}

    public OrderDetailsPK(String orderId, String itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    // equals and hashCode (required for composite key)
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof OrderDetailsPK)) return false;
        OrderDetailsPK that = (OrderDetailsPK) o;
        return orderId.equals(that.orderId) && itemId.equals(that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, itemId);
    }

    // getters and setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }
}
