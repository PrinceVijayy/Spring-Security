package com.ojas.securesafe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping()
    public String delete() {
        return "This is the Hello request";
    }

}
