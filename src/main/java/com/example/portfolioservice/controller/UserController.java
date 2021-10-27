package com.example.portfolioservice.controller;

import com.example.portfolioservice.model.UserDto;
import com.example.portfolioservice.service.PortfolioService;
import com.example.portfolioservice.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    
    @ApiOperation(value = "특정 유저 정보 조회", notes = "특정 유저의 식별자로 정보를 조회하여 반환")
    @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
                    dataType = "long", defaultValue = "None")
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<EntityModel<UserDto>> findUser(@PathVariable("userId") long userId) {
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
