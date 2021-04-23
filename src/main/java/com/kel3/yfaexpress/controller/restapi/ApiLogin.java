package com.kel3.yfaexpress.controller.restapi;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kel3.yfaexpress.model.entity.User;
import com.kel3.yfaexpress.repository.UserRepository;
import com.kel3.yfaexpress.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class ApiLogin {

    @Autowired
    private UserRepository userRepository;


    // @RequestMapping(value = "/login", method = RequestMethod.GET)
    // public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse
    // response) {
    // ModelAndView mav = new ModelAndView("login");
    // mav.addObject("login", new User());
    // return mav;
    // }

    // User user = userService.validateUser(login);

    // post new data
    @PostMapping
    public ResponseEntity<Boolean> user(@RequestBody User user, HttpServletRequest req) {
        List<User> userCheckList = userRepository.findAll();
        for (int i = 0; i < userCheckList.size(); i++) {
            if (user.getUserName().equals(userCheckList.get(i).getUserName()) && user.getPassword().equals(userCheckList.get(i).getPassword())) {

                req.getSession().setAttribute("username", user.getUserName());
                return ResponseEntity.ok(Boolean.TRUE);
            }
        }
        return ResponseEntity.ok(Boolean.FALSE);
    }
}

