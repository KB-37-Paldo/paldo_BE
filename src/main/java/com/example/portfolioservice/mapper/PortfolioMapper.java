package com.example.portfolioservice.mapper;

import com.example.portfolioservice.model.HoldingsDto;
import com.example.portfolioservice.model.PortfolioDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PortfolioMapper {
    PortfolioDto findByUserId(long user_id);
    long deleteByUserId(long user_id);
    void createPortfolio();
    List<HoldingsDto> getAsset();
}
