package com.example.authspring.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.authspring.Repository.EmployeeRepository;
import com.example.authspring.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String createEmployee(Employee employee){
        try{
            employeeRepository.save(employee);
            Algorithm algorithm = Algorithm.HMAC256("superSecret");
            String token = JWT.create()
                    .withIssuer(employee.getName())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            return "Invalid Signing configuration / Couldn't convert Claims.";
        }
    }
}
