package com.example.portfolioservice.service;

import com.example.portfolioservice.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(long userId);
}
