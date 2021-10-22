package com.example.portfolioservice.service;

import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.mapper.UserMapper;
import com.example.portfolioservice.model.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    SqlSession sqlsession;

    @Override
    public List<UserDto> findAll() {
        return sqlsession.getMapper(UserMapper.class).findAll();
    }
}
