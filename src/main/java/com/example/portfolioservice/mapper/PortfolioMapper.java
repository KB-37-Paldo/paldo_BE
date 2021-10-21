package com.example.portfolioservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.portfolioservice.model.HoldingsDto;
import com.example.portfolioservice.model.PortfolioDto;

@Mapper
public interface PortfolioMapper {
    PortfolioDto findByUserId(long user_id);
    List<PortfolioDto> findAll();
    
    void createPortfolio();
    
    // 자산조회   
    List<HoldingsDto> getAsset();
    
}
