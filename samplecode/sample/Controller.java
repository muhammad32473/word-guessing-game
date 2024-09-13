package com.annas.fullstack.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5500")
public class Controller {

    @Autowired
    private Service service;


    @PostMapping("/add")
    public Users addUser(@RequestBody Users entity)
    {
        return service.addEntity(entity);
    }
}
