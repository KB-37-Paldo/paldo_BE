package com.example.portfolioservice.service;

import java.util.List;

import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.model.HoldingsDto;

public interface PortfolioService {

    // 유저의 포트폴리오 조회
    public PortfolioDto findByUserId(long user_id);

    // 포트폴리오 조회
    public PortfolioDto findByPortfolioId(long portfolio_id);

    // 포트폴리오 전체 조회
    public List<PortfolioDto> findAll();
    // 포트폴리오 생성  
<<<<<<< HEAD
    public void createPortfolio();
    
    
    // 자산조회   
    public List<HoldingsDto> getAsset();
    
=======
//    public List<PortfolioDto> createPortfolio();
>>>>>>> 7566b6ebbce4a86f628df008c7fe793ed3157e31

}
