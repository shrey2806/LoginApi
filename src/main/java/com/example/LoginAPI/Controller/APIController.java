package com.example.LoginAPI.Controller;

import com.example.LoginAPI.Exchanges.LoginRequest;
import com.example.LoginAPI.Exchanges.LoginResponse;
import com.example.LoginAPI.Exchanges.SignUpRequest;
import com.example.LoginAPI.Exchanges.SignUpResponse;
import com.example.LoginAPI.Services.UserServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;



/*
  TODO: 1. Create a "/signup" api and a user model so take user information while signup.

  TODO 2. Create a "/login" api to let the user login using email and password.

  TODO 3. This system should have session management so create another api "/dummy" which is accessible only if user is logged in.
          and if user is logged in then by going on this api, the user session should also be extended.

  TODO 4. Create a "/logout" api which will invalidate the session of the user.

  TODO 5. For fast testing purpose use 1 min as max age of the session.

 */

@RestController
@Log4j2
public class APIController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    ResponseEntity<SignUpResponse> signUpUser(@RequestBody SignUpRequest requestbody){

        SignUpResponse response = userServices.registerUser(requestbody);

        if(response!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.badRequest().body(null);

    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest, HttpServletRequest request)throws Exception{

       try{
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

       }catch (BadCredentialsException e){
           throw new Exception("Incorrect Email or Password", e);
       }

       LoginResponse r = new LoginResponse("You are successfully Logged In");
       return  ResponseEntity.ok(r);



    }

    @GetMapping("/random")
    String random(){
        return "Return Inside Random";
    }



}



/*

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true

 */