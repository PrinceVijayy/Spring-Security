package com.ojas.securesafe.service;

import com.ojas.securesafe.dto.UserDto;
import com.ojas.securesafe.entity.User;

public interface UserService {
    boolean createUser(UserDto userDto);
}
