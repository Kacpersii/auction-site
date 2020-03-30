package edu.uph.ii.platformy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(path = "/")
  //  @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String home(Model model) {
        model.addAttribute("message", "Hello World!!!");
        return "home";
    }

}



