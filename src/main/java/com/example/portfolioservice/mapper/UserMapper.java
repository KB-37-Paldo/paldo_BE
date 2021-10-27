package com.example.portfolioservice.mapper;

import com.example.portfolioservice.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDto findById(long userId);
}
