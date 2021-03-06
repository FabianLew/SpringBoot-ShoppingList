package com.fabianlewandowski.shoppinglist.repository;

import com.fabianlewandowski.shoppinglist.model.ShoppingItem;
import com.fabianlewandowski.shoppinglist.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem,Long> {

    ShoppingItem findShoppingItemById(Long id);
    void deleteShoppingItemById(Long id);
    List<ShoppingItem> findAllByList(ShoppingList list);

}
