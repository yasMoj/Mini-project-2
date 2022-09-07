package com.gruppprojekt2.kvarteret;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemRepository {


private List<Item> itemsList;
    public List<Item> getItems() {
        return itemsList;
    }

    public Item getItem(int id) {
        for (Item item : itemsList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
