package com.example.portfolioservice.service;

import java.util.List;

import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.model.HoldingsDto;

public interface PortfolioService {

    // 유저의 포트폴리오 조회
    PortfolioDto findByUserId(long user_id);

    // 포트폴리오 조회
    PortfolioDto findByPortfolioId(long portfolio_id);

<<<<<<< HEAD
    // 포트폴리오 전체 조회
    public List<PortfolioDto> findAll();
    // 포트폴리오 생성  
=======
    // 포트폴리오 삭제
    long deleteByUserId(long user_id);

    // 포트폴리오 생성
>>>>>>> c95ad8671844219dd7c436554cc0409167d25901
    public void createPortfolio();

    // 자산조회   
    public List<HoldingsDto> getAsset();
<<<<<<< HEAD
    
=======
>>>>>>> c95ad8671844219dd7c436554cc0409167d25901

}
