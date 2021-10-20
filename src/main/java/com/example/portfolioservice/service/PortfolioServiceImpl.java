package com.example.portfolioservice.service;


import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.mapper.UserMapper;
import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.model.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService{

    @Autowired
    SqlSession sqlsession;

    @Override
    public List<UserDto> findAll() {
        return sqlsession.getMapper(UserMapper.class).findAll();
    }

    @Override
    public PortfolioDto findByUserId(long user_id) {
        return sqlsession.getMapper(PortfolioMapper.class).findByUserId(user_id);
    }

    @Override
    public PortfolioDto findByPortfolioId(long portfolio_id) {
        return null;
    }

    @Override
    public List<PortfolioDto> findPortfolioAll() {
        return sqlsession.getMapper(PortfolioMapper.class).findAll();
    }


}
