package com.gruppprojekt2.kvarteret;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepository {

    private List<User> userList;

    public  UserRepository() {
        userList = new ArrayList<>();
        userList.add(new User(1, "Kalle", "ason", "blabla@gmail.com", "111"));
        userList.add(new User(2, "Flu", "bson", "blabla@gmail.com", "222"));
        userList.add(new User(3, "Ulf", "cson", "blabla@gmail.com","333"));
        userList.add(new User(4, "Rebecca", "dson", "blabla@gmail.com", "444"));
        userList.add(new User(5, "Vincent", "eson", "blabla@gmail.com", "555"));
        userList.add(new User(6, "Yasaman", "fson", "blabla@gmail.com", "666"));
    }

    public User getUser(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    // test
    public User getUser(String username)
    {
        for (User user : userList) {
            if (user.getEmail().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User addUser(User user) {
        User lastUser = userList.get(userList.size()-1);
        user.setId(lastUser.getId()+1);
        userList.add(user);
        return user;
    }

    public User editUser(User user) {
        User userToEdit = this.getUser(user.getId());
        if (userToEdit != null) {
            userToEdit.setFirstName(user.getFirstName());
            userToEdit.setLastName(user.getLastName());
            userToEdit.setEmail(user.getEmail());
        }
        return user;
    }

    public void deleteUser(int id) {
        User userToDelete = this.getUser(id);
        if (userToDelete != null) {
            userList.remove(userToDelete);
        }
    }
}
