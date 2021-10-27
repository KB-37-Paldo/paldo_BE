package com.example.portfolioservice.service;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.portfolioservice.model.ProductResponseDto;

public interface RecommendationService {
	
	// 추천된 포트폴리오 조회
	ProductResponseDto recommendByProduct(long user_id) throws IOException, ParseException; 
}
