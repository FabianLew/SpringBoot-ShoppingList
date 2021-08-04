package com.fabianlewandowski.shoppinglist.service;

import com.fabianlewandowski.shoppinglist.model.ShoppingItem;
import com.fabianlewandowski.shoppinglist.model.ShoppingList;
import com.fabianlewandowski.shoppinglist.repository.ShoppingItemRepository;
import com.fabianlewandowski.shoppinglist.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {
    private ShoppingListRepository repository;
    private ShoppingItemRepository itemRepository;

//    private int count = 1;

    @Autowired
    public ShoppingListService(ShoppingListRepository repository, ShoppingItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    public List<ShoppingList> findAll(){
        return repository.findAll();
    }

    public ShoppingList findListById(Long id){
        return repository.findShoppingListById(id);
    }

    public ShoppingList save(){
        List<ShoppingItem> items = itemRepository.findAllByList(null);
        ShoppingList list = new ShoppingList(items);
        for (ShoppingItem item : items){
            item.setList(list);
        }
        return repository.save(list);
    }

    public void deleteListById(Long id){
        repository.deleteShoppingListById(id);
    }
}
