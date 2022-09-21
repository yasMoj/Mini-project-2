package com.gruppprojekt2.kvarteret;

/*import org.apache.catalina.UserDatabase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
 //   List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    @Autowired
    UserSqlRepository repository;

    @GetMapping("/")
    String start() {
        return "startpage";
    }

    @PostMapping("/")
    String firstPage()
    {
        return "items";
    }


    @GetMapping("/newUser")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "newUser";
    }

    @PostMapping("/newUser")
    public String registerUserAccount(@Valid User user, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
            return "newUser";

        model.addAttribute("user",user);
        SecurityConfig.addUser(user.email,user.password);
        repository.save(user);
        return "newUser";
    }
}
