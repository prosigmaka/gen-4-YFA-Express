package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.kel3.yfaexpress.model.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
    Users save(UserRegistrationDto registrationDto);
}

