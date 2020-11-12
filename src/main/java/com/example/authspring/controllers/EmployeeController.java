package com.example.authspring.controllers;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authspring.entities.Employee;
import com.example.authspring.security.JwtVerifyToken;
import com.example.authspring.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtVerifyToken jwtVerifyToken;

    @GetMapping("/getemployees")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<Employee> getEmployeeByToken(@RequestHeader String token){
        String email = jwtVerifyToken.verifyToken(token);
        return new ResponseEntity<Employee>(employeeService.getEmployeByEmail(email), HttpStatus.OK);
    }
}
