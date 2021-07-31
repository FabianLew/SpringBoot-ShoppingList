package com.fabianlewandowski.shoppinglist.repository;

import com.fabianlewandowski.shoppinglist.model.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem,Long> {

    ShoppingItem findShoppingItemById(Long id);
    void deleteShoppingItemById(Long id);


}
