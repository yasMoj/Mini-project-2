package com.gruppprojekt2.kvarteret;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ItemRepository {

private List<Item> itemsList;

    // Initierar några objekt bara för att testa
    public ItemRepository()
    {
        itemsList = new ArrayList<>();

        for(int i = 1;i<=12;i++)
        {
            String productName = "";
            int randomProduct = ThreadLocalRandom.current().nextInt(0, 6);
            int randomPrice = ThreadLocalRandom.current().nextInt(20, 5000);
            switch (randomProduct) {
                case 0 -> productName = "Elvisp";
                case 1 -> productName = "Såg";
                case 2 -> productName = "Dammsugare";
                case 3 -> productName = "Borrmaskin";
                case 4 -> productName = "Häst";
                case 5 -> productName = "Cykelvagn";
            }

            itemsList.add(new Item(i,productName,randomPrice, "Det här är bara en tom beskrivning av annons " + i));
        }
    }

    public List<Item> getItems() {
        return itemsList;
    }

    public void addItem(Item item)
    {
        int lastId = itemsList.get(itemsList.size()-1).getId()+1;
        item.setId(lastId);
        itemsList.add(item);
    }



    /*public ItemRepository() {
        itemsList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            itemsList.add(new Item(10+i, "Name " + i,  40 + i + 1.99, "Låna detta" + ("blabla"+i)));
        }
    }*/
    public Item getItem(int id) {
        for (Item item : itemsList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public List<Item> items()
    {
        return itemsList;
    }

    public void removeItem(Item item)
    {
        if (itemsList.contains(item))
            itemsList.remove(item);
    }

    public List<Item> getPage(int page, int pageSize) {
        int from = Math.max(0,page*pageSize);
        int to = Math.min(itemsList.size(),(page+1)*pageSize);

        return itemsList.subList(from, to);
    }

    public int numberOfPages(int pageSize)
    {
        return (int)Math.ceil((double)itemsList.size() / pageSize);
    }

}
