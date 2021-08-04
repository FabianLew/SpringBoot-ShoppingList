package com.fabianlewandowski.shoppinglist.controller;

import com.fabianlewandowski.shoppinglist.model.ShoppingItem;
import com.fabianlewandowski.shoppinglist.model.ShoppingList;
import com.fabianlewandowski.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("lists")
public class ShoppingListController {

    private ShoppingListService service;

    @Autowired
    public ShoppingListController(ShoppingListService service) {
        this.service = service;
    }

    @GetMapping
    public List<ShoppingList> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ShoppingList getListById(@PathVariable Long id){
        return service.findListById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteListById(@PathVariable Long id){
        service.deleteListById(id);
    }

    @PostMapping
    public ShoppingList saveNewList(){
        return service.save();
    }
}
