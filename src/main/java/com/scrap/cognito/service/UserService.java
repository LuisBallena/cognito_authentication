package com.scrap.cognito.service;

import com.scrap.cognito.api.dto.ConfirmDTO;
import com.scrap.cognito.api.dto.UserDTO;
import com.scrap.cognito.entity.UserEntity;
import com.scrap.cognito.facade.CognitoFacade;
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
    @Autowired
    private CognitoFacade cognitoFacade;


    public UserEntity addUser(UserDTO userDTO) {
        cognitoFacade.signUp(userDTO);
        UserEntity userEntity = parseToUserEntity(userDTO);
        return userRepository.save(userEntity);
    }

    public void confirmUser(ConfirmDTO confirmDTO) {
        cognitoFacade.confirmCode(confirmDTO);
    }

    private UserEntity parseToUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        return userEntity;
    }

}
