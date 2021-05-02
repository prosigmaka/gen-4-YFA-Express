package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.dto.UserRegistrationDto;
import com.kel3.yfaexpress.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class ApiUser {

	private final UserService userService;

	public ApiUser(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}

	@RequestMapping(value = "/username", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> currentUserName(Authentication authentication) {
		Map<String, String> map = new HashMap<>();
		map.put("email", authentication.getName());
		return map;
	}
}
