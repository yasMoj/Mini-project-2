package com.gruppprojekt2.kvarteret;

/*import org.apache.catalina.UserDatabase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
    @GetMapping("/profile")
    String profile() {
        return "profile";
    }
    @PostMapping("/startpage")
    String profilepage()
    {
        return "profile";
    }

    @GetMapping("/newUser")
    public String showRegistrationForm(Model model) {
        Siteuser user = new Siteuser();
        model.addAttribute("user", user);
        return "newUser";
    }

    @PostMapping("/newUser")
    public String registerUserAccount(@Valid Siteuser user, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
            return "newUser";
        else{
            model.addAttribute("user",user);
            SecurityConfig.addUser(user.email,user.password);
            repository.save(user);
        }

        return "newUser";
    }
}
