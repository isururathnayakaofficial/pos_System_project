package com.example.pos_system.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Item {
    @Id
    private String iid;
    private String iname;
    private String iquantity;
    @ManyToMany(mappedBy = "items")
    private List<Customer> customers;


    public Item(String iid, String iName, String iQuantity) {
        this.iid = iid;
        this.iname = iName;
        this.iquantity = iQuantity;

    }
}
