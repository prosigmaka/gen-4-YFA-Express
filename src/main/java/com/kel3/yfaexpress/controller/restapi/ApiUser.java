package com.kel3.yfaexpress.controller.restapi;


import com.kel3.yfaexpress.model.dto.UserDto;
import com.kel3.yfaexpress.model.entity.User;
// kurang userRole
//kurang commonIdDto
import com.kel3.yfaexpress.repository.UserRepository;
import com.kel3.yfaexpress.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ApiUser {

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private UserRoleRepository userRoleRepository;


	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;



	// get all
	@GetMapping("/getAll")
	public List<UserDto> getListUser() {
		List<User> userList = userRepository.findAllByisDelete(false);
		List<UserDto> userDtos = new ArrayList<>();

		for (int i = 0; i < userList.size(); i++) {
			UserDto userDto = new UserDto();
			userDto.setIdUser(userList.get(i).getIdUser());
			userDto.setUserName(userList.get(i).getUserName());
			userDto.setPassword(userList.get(i).getPassword());
			userDto.setFullName(userList.get(i).getFullName());
		}

		return userDtos;
	}
	@PostMapping
	public UserDto editSaveUser(@RequestBody UserDto userDto) {
		userService.saveUserDto(userDto);
		return userDto;
	}

	@PostMapping("/regis")
	public UserDto SaveRegisUser(@RequestBody UserDto userDto) {
		userService.saveRegis(userDto);
		return userDto;
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody User user) {
		User userNew = userRepository.findById(id).get();
		modelMapper.map(user, userNew);
		final User updateUser = userRepository.save(userNew);
		return ResponseEntity.ok(updateUser);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		userService.deleteFromUser(id);
	}
}
