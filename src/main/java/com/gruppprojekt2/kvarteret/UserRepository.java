package com.gruppprojekt2.kvarteret;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepository {

    private List<Siteuser> userList;

    public  UserRepository() {
        userList = new ArrayList<>();
        userList.add(new Siteuser(1, "Kalle", "ason", "blabla@gmail.com", "111"));
        userList.add(new Siteuser(2, "Flu", "bson", "blabla@gmail.com", "222"));
        userList.add(new Siteuser(3, "Ulf", "cson", "blabla@gmail.com","333"));
        userList.add(new Siteuser(4, "Rebecca", "dson", "blabla@gmail.com", "444"));
        userList.add(new Siteuser(5, "Vincent", "eson", "blabla@gmail.com", "555"));
        userList.add(new Siteuser(6, "Yasaman", "fson", "blabla@gmail.com", "666"));
    }

    public Siteuser getUser(int id) {
        for (Siteuser user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    // test
    public Siteuser getUser(String username)
    {
        for (Siteuser user : userList) {
            if (user.getEmail().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<Siteuser> getUserList() {
        return userList;
    }

    public void setUserList(List<Siteuser> userList) {
        this.userList = userList;
    }

    public Siteuser addUser(Siteuser user) {
        Siteuser lastUser = userList.get(userList.size()-1);
        user.setId(lastUser.getId()+1);
        userList.add(user);
        return user;
    }

    public Siteuser editUser(Siteuser user) {
        Siteuser userToEdit = this.getUser(user.getId());
        if (userToEdit != null) {
            userToEdit.setFirstName(user.getFirstName());
            userToEdit.setLastName(user.getLastName());
            userToEdit.setEmail(user.getEmail());
        }
        return user;
    }

    public void deleteUser(int id) {
        Siteuser userToDelete = this.getUser(id);
        if (userToDelete != null) {
            userList.remove(userToDelete);
        }
    }
}
