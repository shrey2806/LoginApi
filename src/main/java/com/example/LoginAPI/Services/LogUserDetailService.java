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


    /*

        This method loads User Details with "Email"(used in place of "username")
        and parse them into User object

        Here Return type is UserDetails which is an Interface and is implemented by User Class

     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> entityOptional = userRepository.findByEmail(username);

        if(!entityOptional.isPresent()){
            throw new  UsernameNotFoundException("Not found : " + username );
        }

        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority("ROLE_USER"));


        return new User(entityOptional.get().getEmail(),entityOptional.get().getPassword(),list);

    }
}
