package com.gaurav.bugtrackingsystem.services;

import com.gaurav.bugtrackingsystem.exceptions.InvalidPasswordException;
import com.gaurav.bugtrackingsystem.exceptions.UserNameAlreadyExist;
import com.gaurav.bugtrackingsystem.models.RoleType;
import com.gaurav.bugtrackingsystem.models.User;
import com.gaurav.bugtrackingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String name, String password) throws UserNameAlreadyExist, InvalidPasswordException {
        Optional<User> user = userRepository.findByName(name);
        if(user.isPresent()) {
            // if username already exist
            throw new UserNameAlreadyExist(name);
        }
        if(password.length() < 6) {
            throw new InvalidPasswordException(password);
        }
        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(password);
        newUser.setRoleType(RoleType.BASE_USER);
        return userRepository.saveAndFlush(newUser);
    }
}
