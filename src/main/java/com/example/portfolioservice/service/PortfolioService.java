package com.example.portfolioservice.service;

import com.example.portfolioservice.model.PortfolioDto;

import java.util.List;

public interface PortfolioService {

    // 유저의 포트폴리오 조회
    public PortfolioDto findByUserId(long user_id);

    // 포트폴리오 조회
    public PortfolioDto findByPortfolioId(long portfolio_id);

    // 포트폴리오 전체 조회
    public List<PortfolioDto> findAll();
    // 포트폴리오 생성  
    public List<PortfolioDto> createPortfolio();

}
