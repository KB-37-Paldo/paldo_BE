package com.example.portfolioservice.service;


import java.util.List;
import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.model.PortfolioDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PortfolioServiceImpl implements PortfolioService{

    @Autowired
    SqlSession sqlsession;

    @Override
    public List<PortfolioDto> findAll() {
        return sqlsession.getMapper(PortfolioMapper.class).findAll();
    }

    @Override
    public long deleteByUserId(long user_id) {
        return sqlsession.getMapper(PortfolioMapper.class).deleteByUserId(user_id);
    }

    @Override
    public PortfolioDto findByUserId(long user_id) {
        return sqlsession.getMapper(PortfolioMapper.class).findByUserId(user_id);
    }

    @Override
    public PortfolioDto findByPortfolioId(long portfolio_id) {
        return null;
    }


}
