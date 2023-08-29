package com.example.javaspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Task{

    @GetMapping(name="add", path="/add")
    public Integer addition(@RequestParam Integer a, @RequestParam Integer b){
        return a+b;
    }

    @GetMapping(name="getName", path="/getname")
    public String getName(@RequestParam String name){
        return name;
    }
}
