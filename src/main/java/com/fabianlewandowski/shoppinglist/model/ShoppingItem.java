package com.fabianlewandowski.shoppinglist.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ShoppingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int amount;

    @JsonBackReference
    @ManyToOne
    private ShoppingList list;

    public ShoppingItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public ShoppingItem() {
    }

}
