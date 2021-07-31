package com.fabianlewandowski.shoppinglist.controller;

import com.fabianlewandowski.shoppinglist.model.ShoppingItem;
import com.fabianlewandowski.shoppinglist.service.ShoppingItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/items")
public class ShoppingItemController {

    ShoppingItemService service;
    ObjectMapper objectMapper;

    @Autowired
    public ShoppingItemController(ObjectMapper objectMapper, ShoppingItemService service){
        this.objectMapper = objectMapper;
        this.service = service;
    }

    @GetMapping
    public List<ShoppingItem> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ShoppingItem findShoppingItemById(@PathVariable Long id){
        return service.findShoppingItemById(id);
    }

    @PostMapping
    public ShoppingItem saveNewItem(@RequestBody ShoppingItem item){
        return service.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteShoppingItemById(id);
    }

    @PatchMapping(path = "/{id}" , consumes = "application/json-patch+json")
    public ShoppingItem updateAmount(@PathVariable Long id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        ShoppingItem item = service.findShoppingItemById(id);
        ShoppingItem patchedItem = applyPatchToShoppingItem(patch, item);
        service.updateShoppingItem(patchedItem, id);
        return patchedItem;
    }

    private ShoppingItem applyPatchToShoppingItem(JsonPatch path, ShoppingItem targetItem) throws JsonPatchException, JsonProcessingException {
        JsonNode node = path.apply(objectMapper.convertValue(targetItem, JsonNode.class));
        return objectMapper.treeToValue(node, ShoppingItem.class);
    }

}
