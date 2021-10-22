package com.example.portfolioservice.service;


import java.util.List;
<<<<<<< HEAD

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.model.HoldingsDto;
import com.example.portfolioservice.model.PortfolioDto;
=======
import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.model.PortfolioDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.portfolioservice.model.HoldingsDto;
>>>>>>> c95ad8671844219dd7c436554cc0409167d25901

@Service
public class PortfolioServiceImpl implements PortfolioService{

    @Autowired
    SqlSession sqlsession;

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
<<<<<<< HEAD

    
    
    
=======
>>>>>>> c95ad8671844219dd7c436554cc0409167d25901
    public void createPortfolio(){
    	sqlsession.getMapper(PortfolioMapper.class).createPortfolio();
    }
    
    public List<HoldingsDto> getAsset() {
    	return sqlsession.getMapper(PortfolioMapper.class).getAsset();
    }
<<<<<<< HEAD
    

=======
>>>>>>> c95ad8671844219dd7c436554cc0409167d25901


}
