package com.example.pos_system.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    @Id
    private String cid;
    private String cname;
    private String caddress;
    private String cphone;
    @ManyToMany
    @JoinTable(
            name = "customer_item",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;


    public Customer(String cid, String cname, String caddress, String cphone) {
        this.cid = cid;
        this.cname = cname;
        this.caddress = caddress;
        this.cphone = cphone;

    }
}
