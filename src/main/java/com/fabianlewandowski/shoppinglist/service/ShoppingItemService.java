package com.fabianlewandowski.shoppinglist.service;

import com.fabianlewandowski.shoppinglist.model.ShoppingItem;
import com.fabianlewandowski.shoppinglist.repository.ShoppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingItemService {
    private ShoppingItemRepository repository;

    @Autowired
    public ShoppingItemService(ShoppingItemRepository repository) {
        this.repository = repository;
    }

    public ShoppingItem save(ShoppingItem item){
        return repository.save(item);
    }

    public List<ShoppingItem> findAll(){
        return repository.findAll();
    }

    public void deleteShoppingItemById(Long id){
        repository.deleteShoppingItemById(id);
    }

    public void updateShoppingItem(ShoppingItem item, Long id){
        ShoppingItem myItem = repository.findShoppingItemById(id);
        myItem.setAmount(item.getAmount());
        repository.save(myItem);
    }

    public ShoppingItem findShoppingItemById(Long id) {
        return repository.findShoppingItemById(id);
    }
}
