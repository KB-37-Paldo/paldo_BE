package com.example.portfolioservice.controller;

import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.model.ProductResponseDto;
import com.example.portfolioservice.service.RecommendationService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
public class RecommendationController {

	@Autowired
	RecommendationService recommendationService;

	@ApiOperation(value = "추천 상품 조회", notes = "특정 유저의 추천 상품 정보를 반환")
	@ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
    dataType = "long", defaultValue = "None")
	@GetMapping(value = "/recommendation/product/{userId}")
	public ResponseEntity<EntityModel<ProductResponseDto>> getRecommendationProduct(@PathVariable("userId") long user_id) throws IOException, ParseException {
		ProductResponseDto products = recommendationService.recommendByProduct(user_id);
		
		return ResponseEntity.ok().body(
				EntityModel.of(products)
		);
	}
}
