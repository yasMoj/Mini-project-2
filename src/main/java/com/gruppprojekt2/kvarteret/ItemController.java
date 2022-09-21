package com.gruppprojekt2.kvarteret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ItemController
{
    private static final int PAGE_SIZE = 10;

    @Autowired
    ItemSqlRepository itemRepository;
    //ItemRepository itemRepository;

    @GetMapping("/items/{id}")
    public String item(Model model, @PathVariable Integer id) {
        //Item item1 = itemRepository.getItem(id);
        Item item1 = itemRepository.findById(id).get();
        model.addAttribute("item", item1);
        return "item";
    }

    @GetMapping("/items")
    public String items(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        List<Item> items = getPage(page - 1, PAGE_SIZE);
        int pageCount = numberOfPages(PAGE_SIZE);
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

    @GetMapping("/addItem")
    public String addItem(Model model) {
        model.addAttribute("item",new Item());

        return "addItem";
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute Item item, Model model, HttpServletRequest request) {
        model.addAttribute("item",item);
        //itemRepository.save(item);

        //User user = (User)userRepository.getUser(request.getRemoteUser());
        //item.setUserID(user.getId());
        //System.out.printf("New item added:%s",item.getName());

        return "addItem";
    }

    private int[] toArray ( int num){
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    private List<Item> getPage(int page, int pageSize) {
        //List<Book> books = repository.getBooks();
        //List<Book> books = (List)repository.findAll();
        List<Item> items = (List)itemRepository.findAll();

        int from = Math.max(0,page*pageSize);
        int to = Math.min(items.size(),(page+1)*pageSize);

        return items.subList(from, to);
    }

    private int numberOfPages(int pageSize) {
        //List<Book> books = repository.getBooks();
        List<Item> books = (List)itemRepository.findAll();
        return (int)Math.ceil((double) books.size() / pageSize);
    }
}
