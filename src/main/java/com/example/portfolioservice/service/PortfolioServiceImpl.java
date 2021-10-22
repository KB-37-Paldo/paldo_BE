package com.example.portfolioservice.service;


import com.example.portfolioservice.form.PortfolioForm;
import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.model.HoldingsDto;
import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.model.PortfolioResponseDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService{

    @Autowired
    SqlSession sqlsession;

    @Override
    public long deleteByUserId(long user_id) {
        return sqlsession.getMapper(PortfolioMapper.class).deleteByUserId(user_id);
    }

    @Override
    public PortfolioResponseDto findByUserId(long user_id) {
        PortfolioDto portfolio = sqlsession.getMapper(PortfolioMapper.class).findByUserId(user_id);
        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto(portfolio);
        return portfolioResponseDto;
    }

    @Override
    public PortfolioDto findByPortfolioId(long portfolio_id) {
        return null;
    }

    @Override
    public void createPortfolio(){
    	sqlsession.getMapper(PortfolioMapper.class).createPortfolio();
    }

    @Override
    public List<HoldingsDto> getAsset() {
    	return sqlsession.getMapper(PortfolioMapper.class).getAsset();
    }
    @Override
    public long updatePortfolio(PortfolioForm portfolioForm) {
        return sqlsession.getMapper(PortfolioMapper.class).updatePortfolio(portfolioForm);
    }

}
