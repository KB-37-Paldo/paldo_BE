package com.example.portfolioservice.controller;

import com.example.portfolioservice.model.UserDto;
import com.example.portfolioservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<EntityModel<UserDto>>> findAll() {
        List<EntityModel<UserDto>> users = userService.findAll().stream().map(user -> {
            EntityModel<UserDto> userModel = EntityModel.of(user);
            return userModel;
            // 컬렉션으로 반환.
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(users);
    }
}
