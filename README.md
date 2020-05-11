# LoginApi

*Implemented an authentication mechanism for REST services using Spring Security and Spring Session with the help of JDBC.*

*API that uses **H2 Database** for persistence and manages user session using **X-Auth-Token***


## API ENDPOINTS :

### "/signup"
- POST REQUEST

Request Format
   ``` 
    {
      email : "xyz@abcd.com"
      password : "qwertyuiop"
    }
  ```
  
Response Format 
   ``` 
    { 
      id : 1                 <--- Auto Generated
      email : "xyz@abcd.com"
      password : "qwertyuiop"
    }
  ```
  ### "/login"
  POST REQUEST 
  
  Request Format
   ``` 
    {
      email : "xyz@abcd.com"
      password : "qwertyuiop"
    }
  ```
  
Response Format 
   ``` 
   "You are successfully logged in"
  ```
  
  ### "/dummy"  ( SECURED API ENDPOINT)
  
  - GET REQUEST
  - REQUEST HEADERS :   ( X-AUTH-TOKEN : "2a2c23ee-1c57-482f-a9f4-5bd83dcab9b6") 
  - Can only be accessed if user is authenticated.
  
  RESPONSE : 
    ```
    "Inside Dummy"
    ```
   
 ### "/logout"
 
 - GET REQUEST 
 - REQUEST HEADERS :   ( X-AUTH-TOKEN : "2a2c23ee-1c57-482f-a9f4-5bd83dcab9b6") 
 
 Invalidates the session
 
 
 
  
  
  
