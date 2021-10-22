package com.example.portfolioservice.service;


import java.util.List;
import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.model.PortfolioResponseDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.portfolioservice.model.HoldingsDto;

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
    public void createPortfolio(){
    	sqlsession.getMapper(PortfolioMapper.class).createPortfolio();
    }
    
    public List<HoldingsDto> getAsset() {
    	return sqlsession.getMapper(PortfolioMapper.class).getAsset();
    }


}
