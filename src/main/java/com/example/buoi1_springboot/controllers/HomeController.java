package com.example.buoi1_springboot.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/product")
    public String home() {
        return "Home page";
    }

    @PostMapping("/product")
    public String create() {
        return "taoj";
    }

    @PutMapping("/product")
    public String update() {
        return "dsfds";
    }
    @DeleteMapping("/product")
    public String delete() {
        return "sdfsdf";
    }
}
