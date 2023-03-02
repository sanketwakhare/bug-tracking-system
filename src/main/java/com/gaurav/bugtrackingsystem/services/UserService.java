package com.gaurav.bugtrackingsystem.services;

import com.gaurav.bugtrackingsystem.exceptions.InvalidCredentialsException;
import com.gaurav.bugtrackingsystem.exceptions.InvalidPasswordException;
import com.gaurav.bugtrackingsystem.exceptions.UserNameAlreadyExistException;
import com.gaurav.bugtrackingsystem.models.RoleType;
import com.gaurav.bugtrackingsystem.models.User;
import com.gaurav.bugtrackingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String name, String password, RoleType roleType) throws UserNameAlreadyExistException, InvalidPasswordException {
        Optional<User> user = userRepository.findByName(name);
        if(user.isPresent()) {
            // if username already exist
            throw new UserNameAlreadyExistException(name);
        }
        if(password.length() < 6) {
            // invalid password
            throw new InvalidPasswordException(password);
        }
        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(password);
        // default user is set to base user
        if(Objects.isNull(roleType)) {
            roleType = RoleType.BASE_USER;
        }
        newUser.setRoleType(roleType);
        return userRepository.saveAndFlush(newUser);
    }

    public User login(String name, String password) throws InvalidCredentialsException {
        Optional<User> dbUser = userRepository.findByNameAndPassword(name, password);
        if(dbUser.isEmpty()) {
            throw new InvalidCredentialsException();
        }
        // return existing user
        return dbUser.get();
    }
}
