package com.example.portfolioservice.service;

import com.example.portfolioservice.model.UserDto;

import java.util.List;

public interface UserService {
    UserDto findById(long userId);
}
