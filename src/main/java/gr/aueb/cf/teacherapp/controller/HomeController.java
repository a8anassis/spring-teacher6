package gr.aueb.cf.teacherapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        // Return the name of the Thymeleaf template (without the .html extension)
        return "index";
    }

}
