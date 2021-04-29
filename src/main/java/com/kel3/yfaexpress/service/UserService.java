package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.dto.UserRegistrationDto;
import com.kel3.yfaexpress.model.entity.Useraa;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	Useraa save(UserRegistrationDto registrationDto);
}
