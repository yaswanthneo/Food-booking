package com.example.FoodDeivery.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeivery.model.Item;
import com.example.FoodDeivery.repository.ItemRepository;


import java.util.List;


@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Get item by ID
    public Item getItemById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.orElse(null);
    }

    // Get all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Create a new item
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // Update item
    public Item updateItem(Long id, Item item) {
        item.setItemId(id);
        Optional<Item> optionalExistingItem = itemRepository.findById(item.getItemId());
        if (optionalExistingItem.isPresent()) {
            // Update the existing item with new values
            // Item existingItem = optionalExistingItem.get();
            // existingItem.setItemId(id);
            return itemRepository.save(item);
            // return itemRepository.save(existingItem);
        } else {
            return null; // Or throw an exception
        }
    }

    // Delete item by ID
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}