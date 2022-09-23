package com.gruppprojekt2.kvarteret;

/*import org.apache.catalina.UserDatabase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class UserController {
 //   List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
 Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserSqlRepository repository;

    @Autowired
    private DataSource dataSource;

    @GetMapping("/")
    String start() {
        logger.info("Logging is running");
        return "startpage";
    }

    @PostMapping("/")
    String firstPage(@RequestParam String username, @RequestParam String password, HttpSession session)
    {
        //System.out.printf("Input name was: %s and password was: %s",username,password);
        //System.out.println();

        String pw = "";
        Siteuser siteuser = new Siteuser();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Siteuser WHERE first_name = '" + username + "'")) {

            if (rs.next()){
                // todo spara hela användaren
                siteuser.setFirstName(rs.getString("first_name"));
                siteuser.setLastName(rs.getString("last_name"));
                siteuser.setEmail(rs.getString("email"));
                siteuser.setPassword(rs.getString("password"));
                //pw = rs.getString("password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Siteuser siteuser = repository.findByUser_name(username);


        if(password.equals(siteuser.getPassword()))
        {
            session.setAttribute("siteuser",siteuser);
            System.out.printf("\n\n\n\nINLOGGAD SOM %s %s med mail %s och password %s",siteuser.firstName,siteuser.lastName,siteuser.getEmail(),siteuser.getPassword());

            return "items";
        }
        return "startpage";
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
