package com.ojas.securesafe.service.impl;

import com.ojas.securesafe.dto.UserDto;
import com.ojas.securesafe.entity.User;
import com.ojas.securesafe.repo.UserRepo;
import com.ojas.securesafe.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public boolean createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepo.save(user);
        return true;
    }
}
