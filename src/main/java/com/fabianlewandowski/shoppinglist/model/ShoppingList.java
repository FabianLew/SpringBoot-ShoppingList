package com.fabianlewandowski.shoppinglist.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @OneToMany(orphanRemoval = true)
    private List<ShoppingItem> items;

    public ShoppingList(List<ShoppingItem> items) {
        this.items = items;
        for(ShoppingItem item : items){
            item.setList(this);
        }
    }

    public ShoppingList() {
    }
}
