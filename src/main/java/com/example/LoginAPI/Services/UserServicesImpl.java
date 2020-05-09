package com.example.LoginAPI.Services;

import com.example.LoginAPI.Exchanges.LoginRequest;
import com.example.LoginAPI.Exchanges.LoginResponse;
import com.example.LoginAPI.Exchanges.SignUpRequest;
import com.example.LoginAPI.Exchanges.SignUpResponse;
import com.example.LoginAPI.Model.UserEntity;
import com.example.LoginAPI.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class UserServicesImpl implements  UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public SignUpResponse registerUser(SignUpRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(request.getPassword());

        return modelMapper.map(userRepository.save(userEntity),SignUpResponse.class);


    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        return null;
    }
}
