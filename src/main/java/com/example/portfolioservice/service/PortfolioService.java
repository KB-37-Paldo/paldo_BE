package com.example.portfolioservice.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.example.portfolioservice.form.PortfolioCreateForm;
import com.example.portfolioservice.form.PortfolioUpdateForm;
import com.example.portfolioservice.model.PortfolioResponseDto;

public interface PortfolioService {

    // 유저의 포트폴리오 조회
    PortfolioResponseDto findByUserId(long userId);

    // 포트폴리오 삭제
    long deleteByUserId(long userId);

    // 포트폴리오 생성
    long createPortfolio(PortfolioCreateForm portfolioCreateForm) throws IOException, ParseException;

    // 포트폴리오 수정
    long updatePortfolio(PortfolioUpdateForm portfolioUpdateForm);

    // User의 포트폴리오가 있는지 확인
    boolean exists(long user_id);

    // 연령별 포트폴리오 조회
    List<PortfolioResponseDto> findAgePortfolio(int age);

    // 자산별 포트폴리오 조회
    List<PortfolioResponseDto> findAssetPortfolio(long asset);

    // 유형별 포트폴리오 조회
    List<PortfolioResponseDto> findInvestTypePortfolio(String investType);
}
