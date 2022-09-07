package com.gruppprojekt2.kvarteret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {
    @Autowired
    ItemRepository itemRepository;
    @GetMapping("/items/{id}")
    public String item(Model model, @PathVariable Integer id) {
        Item item1 = itemRepository.getItem(id);
        model.addAttribute("item", item1);
        return "item";
    }
}
