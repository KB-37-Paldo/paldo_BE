package com.example.portfolioservice.service;

import com.example.portfolioservice.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(long userId);
}
