package org.example.web;

import org.example.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {
    static ArrayList<User> userList = new ArrayList<>();


    @PostMapping("/add")
    public String addUser(@RequestParam String email, String login, String password) {
        User user = new User(email, login, password);
        userList.add(user);

        return "redirect:/user";
    }

    @GetMapping()
    public String mainUserPage() {
        return "user";
    }

    @GetMapping("/all_users")
    public String userPage(Model model) {
        model.addAttribute("users", userList);
        return "all_users";
    }

    @PostMapping("/delete_user/{userToDelete}")
    public String deleteUser(@PathVariable String userToDelete) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).email.equals(userToDelete)) {
                userList.remove(i);
                break;
            }
        }
        return "redirect:/user/all_users";
    }

    @PostMapping("/edit_user/{userToEdit}")
    public String editUser(@PathVariable String userToEdit, Model model) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).email.equals(userToEdit)) {
                model.addAttribute("user", userList.get(i));
                return "edit_user";
            }
        }
        return "redirect:/user/all_users";
    }

    @PostMapping("/submit_edited_user/{old_email}")
    public String submitEditedUser(@PathVariable String old_email,
                                   @RequestParam String email, String login, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).email.equals(old_email)) {
                userList.get(i).email = email;
                userList.get(i).login = login;
                userList.get(i).password = password;
            }
        }

        return "redirect:/user/all_users";
    }

}