package org.example.web;

import org.example.Model.User;
import org.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao UserDao;

    @PostMapping("/add")
    public String addUser(@RequestParam String email, String login, String password) {
        User user = new User(email, login, password);
        UserDao.add(user);

        return "redirect:/user";
    }

    @GetMapping()
    public String mainUserPage() {
        return "user";
    }

    @GetMapping("/all_users")
    public String userPage(Model model) {
        model.addAttribute("users", UserDao.all_users());
        return "all_users";
    }

    @PostMapping("/delete_user/{userToDelete}")
    public String deleteUser(@PathVariable String userToDelete) {
        UserDao.delete(userToDelete);
        return "redirect:/user/all_users";
    }

    @PostMapping("/edit_user/{userToEdit}")
    public String editUser(@PathVariable String userToEdit, Model model) {
        model.addAttribute("user", UserDao.edit(userToEdit));
        return "edit_user";
    }

    @PostMapping("/submit_edited_user/{old_email}")
    public String submitEditedUser(@PathVariable String old_email,
                                   @RequestParam String email, String login, String password) {

        User user = new User(email, login, password);
        System.out.println(user);
        UserDao.submit_editing(old_email, user);

        return "redirect:/user/all_users";
    }

}