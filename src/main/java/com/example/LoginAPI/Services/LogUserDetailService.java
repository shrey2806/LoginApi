package com.example.LoginAPI.Services;

import com.example.LoginAPI.Model.UserEntity;
import com.example.LoginAPI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LogUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> entityOptional = userRepository.findByEmail(username);

        if(!entityOptional.isPresent()){
            throw new  UsernameNotFoundException("Not found : " + username );
        }
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user = new User(entityOptional.get().getEmail(),entityOptional.get().getPassword(),list);

        return user;


    }
}
