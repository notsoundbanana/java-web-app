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
        System.out.println(userList);

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

    @PostMapping("/delete_user/{numToDelete}")
    public String deleteUser(@PathVariable String numToDelete) {
        System.out.println("doing /delete_user");
        System.out.println(numToDelete);

        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).email);
            System.out.println(1);
            if (userList.get(i).email.equals(numToDelete)){
                userList.remove(i);
                break;
            }
        }
        return "redirect:/user/all_users";
    }


}
