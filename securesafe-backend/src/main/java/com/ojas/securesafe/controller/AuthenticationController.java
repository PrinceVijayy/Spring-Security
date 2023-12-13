package com.ojas.securesafe.controller;

import com.ojas.securesafe.dto.UserDto;
import com.ojas.securesafe.exception.UserNotCreatedException;
import com.ojas.securesafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto){
        boolean userCreated = userService.createUser(userDto);
        if(!userCreated){
            throw new UserNotCreatedException("User is not created");
        }else{
          return new ResponseEntity<>(
                  "User created successfully with username : "
                          +userDto.getUsername()
                  , HttpStatus.CREATED);
        }
    }
}
