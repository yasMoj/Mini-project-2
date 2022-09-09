package com.gruppprojekt2.kvarteret;

/*import org.apache.catalina.UserDatabase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
 //   List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    @GetMapping("/")
    String start() {
        return "startpage";
    }

    @PostMapping("/")
    String firstPage() {
        return "items";
    }

    @GetMapping ("/newUser")
   public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }
    @PostMapping("/newUser")
    public String newUser(@ModelAttribute User user, Model model)
    {model.addAttribute("user", user);
       // itemRepository.addItem(item); - behöver en metod motsvarande till user
        return "newUser";
    }
}
