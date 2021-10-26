package com.example.portfolioservice.mapper;

import com.example.portfolioservice.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDto> findAll();
    UserDto findById(long userId);
}
