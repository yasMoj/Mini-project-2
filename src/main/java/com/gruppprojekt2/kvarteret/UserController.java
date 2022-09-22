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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@Controller
public class UserController {
 //   List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
 Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserSqlRepository repository;


    @GetMapping("/")
    String start() {
        logger.info("Logging is running");
        return "startpage";
    }

    @PostMapping("/")
    String firstPage()
    {
        return "items";
    }


    @GetMapping("/newUser")
    public String showRegistrationForm(Model model) {
        Siteuser user = new Siteuser();
        model.addAttribute("user", user);
        logger.info("User tries to make account: " + user);
        return "newUser";
    }

    @PostMapping("/newUser")
    public String registerUserAccount(@Valid Siteuser user, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()) {
            //logger.error("Logging an USER RESULT ERROR message");
            return "newUser";
        }
        else{
            model.addAttribute("user",user);
            SecurityConfig.addUser(user.email,user.password);
            repository.save(user);
            logger.info("User have made new account: " + user.firstName + " " + user.lastName + " " + user.email);
        }

        return "newUser";
    }
}
