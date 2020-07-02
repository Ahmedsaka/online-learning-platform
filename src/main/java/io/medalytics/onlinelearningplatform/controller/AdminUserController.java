package io.medalytics.onlinelearningplatform.controller;

import io.medalytics.onlinelearningplatform.model.User;
import io.medalytics.onlinelearningplatform.model.UserSignUpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/authenticate")
public class AdminUserController {

    public User signUp(@RequestBody UserSignUpRequest request){
        return null;
    }
}
