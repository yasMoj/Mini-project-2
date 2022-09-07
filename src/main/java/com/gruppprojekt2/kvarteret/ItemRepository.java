package com.gruppprojekt2.kvarteret;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemRepository {


private List<Item> itemsList;
    public List<Item> getItems() {
        return itemsList;
    }

    public ItemRepository() {
        itemsList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            itemsList.add(new Item(10+i, "Name " + i,  40 + i + 1.99, "Låna detta" + ("blabla"+i)));
        }
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
