package com.fabianlewandowski.shoppinglist.repository;

import com.fabianlewandowski.shoppinglist.model.ShoppingItem;
import com.fabianlewandowski.shoppinglist.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    ShoppingList findShoppingListByItems(ShoppingItem item);
    ShoppingList findShoppingListById(Long id);
    void deleteShoppingListById(Long id);
}
