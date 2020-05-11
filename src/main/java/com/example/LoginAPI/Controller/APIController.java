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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;



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
    private AuthenticationManager authenticationManager;


    /* API ENDPOINT to REGISTER USER
       REQUEST FORMAT:
       {
          email : "xyz@abcd.com"
          password : "qwertyuiop"

       }


       RESPONSE FORMAT:
       {
          id: 1                                <---- Auto Generated
          email : "xyz@abcd.com"
          password : "qwertyuiop"

       }  */
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUpUser(@RequestBody SignUpRequest requestbody) {

        SignUpResponse response = userServices.registerUser(requestbody);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.badRequest().body(null);

    }




    /*
        METHOD TO LOGIN USER

        - User is authenticated and a session token called X-Auth Token is generated in Response Header
        - Subsequent Request can be authenticated using that X-Auth-Token in the request header.
        - The Session maximum inactive interval is set to 60 seconds. After 60 Seconds the session is invalid.


        REQUEST FORMAT :

         {
           email : "xyz@abcd.com"
           password : "qwertyuiop"

         }

         RESPONSE :
         On SUCCESS :

           String ( "You are successfully Logged In"):   */


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest, HttpServletRequest req) throws Exception {


        try {
            Authentication auth =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);

            HttpSession session = req.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
            session.setMaxInactiveInterval(60);

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect Email or Password", e);
        }

        LoginResponse r = new LoginResponse("You are successfully Logged In");
        return ResponseEntity.ok(r);


    }


    /*
        SECURED API ENDPOINT :
        Only be accessed if User is authenticated. (Request Header should include  generated X-Auth-Token)
        Session is refreshed again on accessing this endpoint. */

    @GetMapping("/dummy")
    public String dummy() {
        return "Return Inside Dummy";
    }



    /*
       API ENDPOINT TO LOGOUT USER OR INVALIDATE THE SESSION.

     */

    @GetMapping("/logout")
    public void logoutUser(HttpSession session) {
        session.invalidate();
    }



}



