package com.kel3.yfaexpress.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRequestDto {
    private String userName;
    private String password;
    private List<RoleRequestDto> roles;

}