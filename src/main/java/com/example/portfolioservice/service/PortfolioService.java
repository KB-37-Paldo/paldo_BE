package com.example.portfolioservice.service;

import java.util.List;

import com.example.portfolioservice.form.PortfolioForm;
import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.model.HoldingsDto;
import com.example.portfolioservice.model.PortfolioResponseDto;

public interface PortfolioService {

    // 유저의 포트폴리오 조회
    PortfolioResponseDto findByUserId(long user_id);

    // 포트폴리오 조회
    PortfolioDto findByPortfolioId(long portfolio_id);

    // 포트폴리오 삭제
    long deleteByUserId(long user_id);

    // 포트폴리오 생성
    public void createPortfolio();

    // 자산조회   
    public List<HoldingsDto> getAsset();

    // 포트폴리오 수정
    long updatePortfolio(PortfolioForm portfolioForm);
}
