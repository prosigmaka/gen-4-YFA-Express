package com.kel3.yfaexpress.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kel3.yfaexpress.model.entity.User;
import com.kel3.yfaexpress.model.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}

