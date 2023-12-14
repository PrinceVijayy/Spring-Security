package com.ojas.securesafe.controller;

import com.ojas.securesafe.dto.UserDto;
import com.ojas.securesafe.exception.UserNotCreatedException;
import com.ojas.securesafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/securesafe")
public class AuthenticationController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthenticationController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto){
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
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

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello",HttpStatus.OK);
    }
}
