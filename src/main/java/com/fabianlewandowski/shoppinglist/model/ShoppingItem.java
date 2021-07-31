package com.fabianlewandowski.shoppinglist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ShoppingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int amount;

    public ShoppingItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public ShoppingItem() {
    }
}
