package com.fabianlewandowski.shoppinglist;

import com.fabianlewandowski.shoppinglist.model.ShoppingItem;
import com.fabianlewandowski.shoppinglist.repository.ShoppingItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingListApplication.class, args);
    }


}
