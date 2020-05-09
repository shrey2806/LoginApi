package com.example.LoginAPI.Controller;

import com.example.LoginAPI.Exchanges.SignUpRequest;
import com.example.LoginAPI.Exchanges.SignUpResponse;
import com.example.LoginAPI.Services.UserServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



/*
TODO: 1. Create a "/signup" api and a user model so take user information while signup.
           TODO 2. Create a "/login" api to let the user login using email and password.

           TODO 3. This system should have session management so create another api "/dummy" which is accessible only if user is logged in.
                   and if user is logged in then by going on this api, the user session should also be extended.

           TODO 4. Create a "/logout" api which will invalifdate the session of the user.

           TODO 5. For fast testing purpose use 1 min as max age of the session.

 */

@RestController
@Log4j2
public class APIController {

    @Autowired
    UserServices userServices;

    @PostMapping("/signup")
    ResponseEntity<SignUpResponse> signUpUser(@RequestBody SignUpRequest requestbody){

        SignUpResponse response = userServices.registerUser(requestbody);

        if(response!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.badRequest().body(null);

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