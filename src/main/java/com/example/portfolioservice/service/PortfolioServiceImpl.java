package com.example.portfolioservice.service;


<<<<<<< HEAD
import java.util.List;

=======
import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.model.PortfolioDto;
>>>>>>> 321e43cc40e5eadfe1f473da1b0d3aa8a9b1d84a
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.model.PortfolioDto;

@Service
public class PortfolioServiceImpl implements PortfolioService{

    @Autowired
    SqlSession sqlsession;

    @Override
    public List<PortfolioDto> findAll() {
        return sqlsession.getMapper(PortfolioMapper.class).findAll();
    }

    @Override
    public PortfolioDto findByUserId(long user_id) {
        return sqlsession.getMapper(PortfolioMapper.class).findByUserId(user_id);
    }

    @Override
    public PortfolioDto findByPortfolioId(long portfolio_id) {
        return null;
    }
    
    
    
    public List<PortfolioDto> createPortfolio(){
    	return sqlsession.getMapper(PortfolioMapper.class).createPortfolio();
    }



}
