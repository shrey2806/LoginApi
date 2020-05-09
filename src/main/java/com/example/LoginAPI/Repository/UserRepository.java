package com.example.LoginAPI.Repository;

import com.example.LoginAPI.Model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {

    Optional<UserEntity> findByEmail(String email);

}
