package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSecurityService extends UserDetailsService {
    User save(User user);
}
