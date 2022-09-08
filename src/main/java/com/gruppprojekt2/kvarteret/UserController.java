package com.gruppprojekt2.kvarteret;

/*import org.apache.catalina.UserDatabase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    String newuser () {
        return "newUser";
    }


}
