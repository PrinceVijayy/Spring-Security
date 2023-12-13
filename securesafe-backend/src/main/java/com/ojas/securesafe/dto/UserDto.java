package com.ojas.securesafe.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull
    String username;
    @NotNull
    String password;
}
