package com.example.portfolioservice.controller;

import com.example.portfolioservice.model.UserDto;
import com.example.portfolioservice.service.PortfolioService;
import com.example.portfolioservice.service.UserService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PortfolioService portfolioService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<EntityModel<UserDto>>> findAll() {
        return ResponseEntity.ok().body(userService.findAll().stream().map(user -> {
                EntityModel<UserDto> userModel = EntityModel.of(user);
                return userModel;
            }).collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/user/{userID}")
    public ResponseEntity<EntityModel<UserDto>> findUser(@PathVariable("userID") long userId) {
        UserDto user = userService.findById(userId);

        EntityModel<UserDto> entityModel = EntityModel.of(user)
                .add(linkTo(methodOn(UserController.class).findUser(userId)).withSelfRel());

        WebMvcLinkBuilder portfolioLink= linkTo(PortfolioController.class).slash(userId);

        if (portfolioService.exists(userId)) {
            entityModel.add(portfolioLink.withRel("portfolio"));
            user.setPortfolio(true);
        }else {
            entityModel.add(portfolioLink.withRel("portfolioCreate"));
            user.setPortfolio(false);
        }

        return ResponseEntity.ok().body(entityModel);
    }
}
