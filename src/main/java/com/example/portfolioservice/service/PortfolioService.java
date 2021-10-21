package com.example.portfolioservice.service;

import com.example.portfolioservice.model.PortfolioDto;

import java.util.List;

public interface PortfolioService {

    // 유저의 포트폴리오 조회
    public PortfolioDto findByUserID(Long user_id);

    // 포트폴리오 조회
    public PortfolioDto findByPortfolioId(Long portfolio_id);

}
