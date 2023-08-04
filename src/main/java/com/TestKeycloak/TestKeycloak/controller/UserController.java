package com.TestKeycloak.TestKeycloak.controller;

import com.TestKeycloak.TestKeycloak.model.User;
import com.TestKeycloak.TestKeycloak.service.UserService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UserController {


        @Autowired
        UserService userService;

//        @RequestMapping(value = "/user", method = RequestMethod.GET)
//        public List<UserRepresentation> createUser() {
//            return userService.getAllUsers();
//        }

}
