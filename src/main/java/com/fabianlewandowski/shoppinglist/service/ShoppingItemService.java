package com.fabianlewandowski.shoppinglist.service;

import com.fabianlewandowski.shoppinglist.model.ShoppingItem;
import com.fabianlewandowski.shoppinglist.model.ShoppingList;
import com.fabianlewandowski.shoppinglist.repository.ShoppingItemRepository;
import com.fabianlewandowski.shoppinglist.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingItemService {
    private ShoppingItemRepository repository;
    private ShoppingListRepository listRepository;

    @Autowired
    public ShoppingItemService(ShoppingItemRepository repository, ShoppingListRepository listRepository) {
        this.repository = repository;
        this.listRepository = listRepository;
    }

    public ShoppingItem save(ShoppingItem item){
        return repository.save(item);
    }

    public List<ShoppingItem> findAll(){
        return repository.findAll();
    }

    public void deleteShoppingItemById(Long id){
        ShoppingItem item = repository.findShoppingItemById(id);
        try {
            ShoppingList list = listRepository.findShoppingListByItems(item);
            list.getItems().remove(item);
            listRepository.save(list);
            repository.deleteShoppingItemById(id);
        }catch (Exception e){
            repository.deleteShoppingItemById(id);
        }
    }

    public void updateShoppingItem(ShoppingItem item, Long id){
        ShoppingItem myItem = repository.findShoppingItemById(id);
        myItem.setAmount(item.getAmount());
        repository.save(myItem);
    }

    public ShoppingItem findShoppingItemById(Long id) {
        return repository.findShoppingItemById(id);
    }

    public List<ShoppingItem> findShoppingItemsWithNoList(){
        return repository.findAllByList(null);
    }

}
