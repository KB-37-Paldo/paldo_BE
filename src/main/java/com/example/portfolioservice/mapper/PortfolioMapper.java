package com.example.portfolioservice.mapper;

import com.example.portfolioservice.model.PortfolioDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PortfolioMapper {
    PortfolioDto findByUserId(long user_id);
    List<PortfolioDto> findAll();
}
