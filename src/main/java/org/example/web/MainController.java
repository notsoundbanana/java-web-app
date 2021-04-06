package org.example.web;

import org.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/")
public class MainController {
    @Autowired
    UserDao userDao;


    @GetMapping("/hello")
    public String helloPage(Model model){
        model.addAttribute("text", "Hello world");
        return "index";  //
    }

}
