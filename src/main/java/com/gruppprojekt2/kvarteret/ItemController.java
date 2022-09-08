package com.gruppprojekt2.kvarteret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemController {

    private static final int PAGE_SIZE = 10;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items/{id}")
    public String item(Model model, @PathVariable Integer id) {
        Item item1 = itemRepository.getItem(id);
        model.addAttribute("item", item1);
        return "item";
    }

    @GetMapping("/items")
    public String items(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        List<Item> items = itemRepository.getPage(page - 1, PAGE_SIZE);
        int pageCount = itemRepository.numberOfPages(PAGE_SIZE);
        int[] pages = toArray(pageCount);

        model.addAttribute("items", items);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);
        return "items";
    }


    /*
    @GetMapping("/items/")
    public String itemPage() {
        return "items";
    }*/

    @GetMapping("/addItem/")
    public String addItem(Model model) {
        Item item = new Item();
        itemRepository.addItem(item);
        return "addItem";
    }

    @PostMapping("/addItem/")
    public String addItem(@ModelAttribute Item item, Model model) {
        itemRepository.addItem(item);

        return "addItem";
    }
    private int[] toArray ( int num){
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = i + 1;
        }
        return result;
    }
}
