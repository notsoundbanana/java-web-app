package org.example.web;

import org.example.Main;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller()
@RequestMapping("/")
public class MainController {

    @GetMapping("/hello")
    public String helloPage(Model model){
        model.addAttribute("text", "Hello world");

        return "index";  //
    }

}
