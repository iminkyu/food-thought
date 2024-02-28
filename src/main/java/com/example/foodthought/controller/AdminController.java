package com.example.foodthought.controller;

import com.example.foodthought.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    //user

    @GetMapping()
    public ResponseEntity test(){
        return ResponseEntity.ok().build();
    }
    //board

    //comment

    //book
}