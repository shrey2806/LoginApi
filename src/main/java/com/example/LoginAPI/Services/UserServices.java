package com.example.LoginAPI.Services;

import com.example.LoginAPI.Exchanges.SignUpRequest;
import com.example.LoginAPI.Exchanges.SignUpResponse;

public interface UserServices {

    SignUpResponse registerUser(SignUpRequest request);


}
