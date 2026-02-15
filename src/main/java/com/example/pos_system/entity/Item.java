package com.example.pos_system.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private String iid;
    private String iname;
    private String iquantity;
    private BigDecimal iprice;
    @ManyToMany(mappedBy = "items")
    private List<Customer> customers;
    @OneToMany(mappedBy = "item")
    private List<OrderDetails> orderDetails;





    public Item(String iid, String iName, String iQuantity, BigDecimal iprice) {
        this.iid = iid;
        this.iname = iName;
        this.iquantity = iQuantity;
        this.iprice = iprice;
    }

    public String getItemId() {
        return iid;
    }
}
