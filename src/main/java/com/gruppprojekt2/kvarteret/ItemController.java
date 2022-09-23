package com.gruppprojekt2.kvarteret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ItemController
{
    private static final int PAGE_SIZE = 10;
    Logger logger = LoggerFactory.getLogger(ItemController.class);
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
    public String addItem(@ModelAttribute Item item, Model model, HttpSession session, HttpServletRequest request, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        model.addAttribute("item",item);

        // Hämta filnamnet
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        // Har användaren inte laddat upp en bild så vill fortsätta använda default
        if(!fileName.equals(""))
        {
            item.setImg(fileName);

            // Lägg till bilden i projektmappen /images/[bildnamn.typ]
            /* Med FileUploadUtil

            String uploadDir = "images/" + item.getId();
            FileUploadUtil.saveFile(uploadDir, item.getImg(), multipartFile);
             */

            // System.getProperty("user.dir") pekar på C:\Users\...\kvarteret
            String folder = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\ads\\";
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(folder + multipartFile.getOriginalFilename());
            Files.write(path,bytes);

            item.setImg("images/ads/" + item.getImg()); // item.getImg()
        }

        Siteuser siteuser = (Siteuser) session.getAttribute("siteuser");
        item.setSiteuser(siteuser);

        itemRepository.save(item);
        logger.info("User added an item" + " " + item );
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
