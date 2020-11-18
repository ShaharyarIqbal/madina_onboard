package com.example.onboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class OnBoardController {

    List<String> names = new ArrayList<>();

    @GetMapping(value = "/v1/public/user/register")
    public ResponseEntity<String> register(){
        String nameToPrint = names.isEmpty()? "World": names.toString();
        return ResponseEntity.ok("Hello "+ nameToPrint);
    }

    @PostMapping(value = "/v1/public/user/onboard/{name}")
    public ResponseEntity<String> save(@PathVariable("name") String name) {
        names.add(name);
        return ResponseEntity.ok("Hello "+name);
    }

}
