package com.gruppprojekt2.kvarteret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ItemController
{
    private final int PAGE_SIZE = 10;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items")
    public String books(Model model, @RequestParam(value="page", required=false, defaultValue="1") int page) {

        List<Item> items = itemRepository.getPage(page-1, PAGE_SIZE);
        int pageCount = itemRepository.numberOfPages(PAGE_SIZE);
        int[] pages = toArray(pageCount);

        model.addAttribute("books", itemRepository);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);
        return "items";
    }

    public List<Item> getPage(int page, int pageSize) {
        int from = Math.max(0,page*pageSize);
        int to = Math.min(items.size(),(page+1)*pageSize);

        return items.subList(from, to);
    }

    public int numberOfPages(int pageSize) {
        return (int)Math.ceil((double)books.size() / pageSize);
    }

}
