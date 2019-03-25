package com.scrap.cognito.service;

import com.scrap.cognito.api.dto.UserDTO;
import com.scrap.cognito.entity.UserEntity;
import com.scrap.cognito.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService.
 *
 * @author Luis Alonso Ballena Garcia
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity addUser(UserDTO userDTO) {
        UserEntity userEntity = parseToUserEntity(userDTO);
        return userRepository.save(userEntity);
    }

    private UserEntity parseToUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        return userEntity;
    }

}
