package com.company.model.authentication;

import com.company.model.dao.User;
import com.company.model.repository.UserRepository;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class AuthenticationService {
    private AuthenticationUser authUser;
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public AuthenticationUser getAuthenticationUser(){
        return this.authUser;
    }

    public boolean authorizeUser(String email, String password) throws IOException {
        Optional<User> userOptional = userRepository.findUserByEmailAndPassword(email, password);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            this.authUser = new AuthenticationUser(user.getUserName(), user.getRole());
            return true;
        }else {
            return false;
        }
    }

    public boolean isAuthorized(){
        return !Objects.isNull(this.authUser);
    }

}
