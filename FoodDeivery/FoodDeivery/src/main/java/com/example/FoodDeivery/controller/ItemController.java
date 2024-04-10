package com.example.FoodDeivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FoodDeivery.model.Item;
import com.example.FoodDeivery.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Create a new item
    @PostMapping
    @Secured("ROLE_RESTAURANT")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item newItem = itemService.createItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    // Get all items
    @GetMapping
    @Secured({"ROLE_RESTAURANT","ROLE_CUSTOMER"})
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // Get item by ID
    @GetMapping("/{id}")
    @Secured("ROLE_RESTAURANT")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) {
        Item item = itemService.getItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    // Update item
    @PutMapping("/{id}")
    @Secured("ROLE_RESTAURANT")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(id, item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    // Delete item by ID
    @DeleteMapping("/{id}")
    @Secured("ROLE_RESTAURANT")
    public ResponseEntity<Void> deleteItemById(@PathVariable("id") Long id) {
        itemService.deleteItemById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
