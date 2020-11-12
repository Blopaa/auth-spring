package com.example.authspring.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.authspring.Repository.EmployeeRepository;
import com.example.authspring.dto.EmployeeRequest;
import com.example.authspring.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String createEmployee(Employee employee){
        try{
            if(employee.getEmail() == null){
                return "You must write an email";
            }else if(employee.getPassword() == null){
                return "You must write a password";
            }else if(employee.getName() == null){
                return "You must write a name";
            }
            employeeRepository.save(employee);
            Algorithm algorithm = Algorithm.HMAC256("superSecret");
            String token = JWT.create()
                    .withClaim("email", employee.getEmail())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            return "Invalid Signing configuration / Couldn't convert Claims.";
        }
    }

    public String SignInUser(EmployeeRequest employee){
        try {
            if(employee.getEmail() == null || employee.getPassword() == null){
                return "You must write an email and a password";
            }
            Employee foundEmployee = employeeRepository.findByEmail(employee.getEmail());
            if(foundEmployee == null){
                return null;
            }

            if(!foundEmployee.getPassword().equals(employee.getPassword())){
                return null;
            }

            Algorithm algorithm = Algorithm.HMAC256("superSecret");
            String token = JWT.create()
                    .withClaim("email", employee.getEmail())
                    .sign(algorithm);
            return token;
        }catch(JWTCreationException e){
            return null;
        }
    }
}
