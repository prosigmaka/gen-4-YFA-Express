package com.kel3.yfaexpress.service;

import com.kel3.yfaexpress.model.dto.UserRegistrationDto;
import com.kel3.yfaexpress.model.entity.Roles;
import com.kel3.yfaexpress.model.entity.Useraa;
import com.kel3.yfaexpress.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public Useraa save(UserRegistrationDto registrationDto) {

				Useraa useraa = new Useraa
				(registrationDto.getFirstName(),
				registrationDto.getLastName(),
				registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()) ,
						Arrays.asList(new Roles("ROLE_USER")));

		return userRepository.save(useraa);
	}

	@Override
	public boolean checkIfUserExist(String email) {
		return userRepository.findByEmail(email) !=null ? true : false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Useraa useraa = userRepository.findByEmail(username);
		if(useraa == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(useraa.getEmail(), useraa.getPassword(), mapRolesToAuthorities(useraa.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}

