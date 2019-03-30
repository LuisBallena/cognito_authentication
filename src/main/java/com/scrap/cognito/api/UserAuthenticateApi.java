package com.scrap.cognito.api;

import com.scrap.cognito.api.dto.ConfirmDTO;
import com.scrap.cognito.api.dto.UserDTO;
import com.scrap.cognito.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserAuthenticateApi.
 *
 * @author Luis Alonso Ballena Garcia
 */
@RestController
@RequestMapping("/user/authenticate")
public class UserAuthenticateApi {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity signUp(@Valid @RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/confirm")
    public ResponseEntity confirm(@Valid @RequestBody ConfirmDTO confirmDTO) {
        userService.confirmUser(confirmDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
