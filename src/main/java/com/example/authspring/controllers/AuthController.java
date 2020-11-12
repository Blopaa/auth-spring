package com.example.authspring.controllers;


import com.example.authspring.entities.Employee;
import com.example.authspring.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/SignUp")
    public ResponseEntity<?> SignUp(@RequestBody Employee newEmployee){
        String employee = authService.createEmployee(newEmployee);
        return new ResponseEntity<String>(employee, HttpStatus.OK);
    }

}
