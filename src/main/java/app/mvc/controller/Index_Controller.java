package app.mvc.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class Index_Controller{

    @GetMapping("/")
    public String index(){

        return "Hello My Project";

    }


}
