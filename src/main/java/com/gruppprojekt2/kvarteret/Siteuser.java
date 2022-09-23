package com.gruppprojekt2.kvarteret;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Siteuser {
    @Id // Primärnyckeln
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    int id;
    @NotEmpty
    //@Column(name = "first_name")
    String firstName;
    @NotEmpty
    //@Column(name = "last_name")
    String lastName;
    @NotEmpty
    @Email
    String email;
    @NotEmpty
    String password;


    @OneToMany(mappedBy = "siteuser", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Siteuser(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Siteuser(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
