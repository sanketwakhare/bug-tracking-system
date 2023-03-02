package com.gaurav.bugtrackingsystem.controllers;

import com.gaurav.bugtrackingsystem.dtos.*;
import com.gaurav.bugtrackingsystem.dtos.user_dtos.*;
import com.gaurav.bugtrackingsystem.exceptions.InvalidCredentialsException;
import com.gaurav.bugtrackingsystem.exceptions.InvalidPasswordException;
import com.gaurav.bugtrackingsystem.exceptions.UserNameAlreadyExistException;
import com.gaurav.bugtrackingsystem.models.RoleType;
import com.gaurav.bugtrackingsystem.models.User;
import com.gaurav.bugtrackingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "signup")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        ResponseEntity<UserSignUpResponseDto> response;
        try {
            String name = userSignUpRequestDto.getName();
            String password = userSignUpRequestDto.getPassword();
            RoleType roleType = userSignUpRequestDto.getRole();
            User user = userService.signUp(name, password, roleType);
            UserSignUpResponseDto userSignUpResponseDto = new UserSignUpResponseDto();
            userSignUpResponseDto.setId(user.getId());
            userSignUpResponseDto.setName(user.getName());
            userSignUpResponseDto.setRole(user.getRoleType());
            response = new ResponseEntity<>(userSignUpResponseDto, HttpStatus.OK);
        } catch (InvalidPasswordException | UserNameAlreadyExistException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        ResponseEntity<UserLoginResponseDto> response;
        try {
            String name = userLoginRequestDto.getName();
            String password = userLoginRequestDto.getPassword();
            User user = userService.login(name, password);
            UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
            userLoginResponseDto.setId(user.getId());
            userLoginResponseDto.setName(user.getName());
            userLoginResponseDto.setRole(user.getRoleType());
            response = new ResponseEntity<>(userLoginResponseDto, HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllUsers(@RequestBody GetAllUsersRequestDto getAllUsersRequestDto) {
        ResponseEntity<GetAllUsersResponseDto> response;
        try {
            String roleType = getAllUsersRequestDto.getRole();
            List<User> users = userService.getAllUsers(roleType);
            GetAllUsersResponseDto getAllUsersResponseDto = new GetAllUsersResponseDto();
            List<UserDto> userDtos = new ArrayList<>();
            users.forEach(user -> userDtos.add(new UserDto(user.getId(), user.getName(), user.getRoleType())));
            getAllUsersResponseDto.setUsers(userDtos);
            response = new ResponseEntity<>(getAllUsersResponseDto, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
