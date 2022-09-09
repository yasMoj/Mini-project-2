package com.gruppprojekt2.kvarteret;

/*import org.apache.catalina.UserDatabase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
 //   List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    User user = new User();

    @GetMapping("/")
    String start() {
        return "startpage";
    }

    @PostMapping("/")
    String firstPage(HttpSession session, @RequestParam String username,@RequestParam String password) {
        if (user.getEmail().equals(username) && user.getPassword().equals(password)) {
            session.setAttribute("username", username);
            return "items";
        }
        return "startpage";
    }

    @GetMapping ("/newUser")
    String newuser () {
        return "newUser";
    }


}
