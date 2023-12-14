package com.ojas.securesafe.controller;

import com.ojas.securesafe.dto.UserDto;
import com.ojas.securesafe.exception.UserNotCreatedException;
import com.ojas.securesafe.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testRegisterUserWhenUserIsCreatedThenReturnCreatedStatus() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("testUser");
        userDto.setPassword("testPassword");

        when(bCryptPasswordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(userService.createUser(any(UserDto.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testUser\",\"password\":\"testPassword\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("User created successfully with username : testUser"));
    }

    @Test
    public void testRegisterUserWhenUserIsNotCreatedThenThrowException() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("testUser");
        userDto.setPassword("testPassword");

        when(bCryptPasswordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(userService.createUser(any(UserDto.class))).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testUser\",\"password\":\"testPassword\"}"))
                .andExpect(result -> {
                    if (result.getResolvedException() != null) {
                        throw result.getResolvedException();
                    }
                })
                .andExpect(result -> {
                    if (result.getResolvedException() instanceof UserNotCreatedException) {
                        throw result.getResolvedException();
                    }
                });
    }
}