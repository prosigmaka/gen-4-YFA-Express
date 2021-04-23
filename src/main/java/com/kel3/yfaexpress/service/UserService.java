package com.kel3.yfaexpress.service;
import com.kel3.yfaexpress.model.entity.User;
import com.kel3.yfaexpress.model.dto.UserDto;
public interface UserService {

    User latTransactional();

    User saveUserMaterRole(User user);

    UserDto saveUserDto(UserDto user);

    UserDto saveRegis(UserDto user);

    UserDto saveEdit(UserDto userDto);

    void deleteFromUser(Integer id);

//	User saveUserAndRole(User user);

//	List<UserDto> saveAllUser(List<UserDto> userDto);

}
