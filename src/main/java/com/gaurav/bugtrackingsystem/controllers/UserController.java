package com.gaurav.bugtrackingsystem.controllers;

import com.gaurav.bugtrackingsystem.dtos.ErrorResponse;
import com.gaurav.bugtrackingsystem.dtos.UserSignUpRequestDto;
import com.gaurav.bugtrackingsystem.dtos.UserSignUpResponseDto;
import com.gaurav.bugtrackingsystem.exceptions.InvalidPasswordException;
import com.gaurav.bugtrackingsystem.exceptions.UserNameAlreadyExistException;
import com.gaurav.bugtrackingsystem.models.User;
import com.gaurav.bugtrackingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        UserSignUpResponseDto userSignUpResponseDto = new UserSignUpResponseDto();
        try {
            String name = userSignUpRequestDto.getName();
            String password = userSignUpRequestDto.getPassword();
            User user = userService.signUp(name, password);
            userSignUpResponseDto.setId(user.getId());
            userSignUpResponseDto.setName(user.getName());
            response = new ResponseEntity<>(userSignUpResponseDto, HttpStatus.OK);
        } catch (InvalidPasswordException | UserNameAlreadyExistException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
