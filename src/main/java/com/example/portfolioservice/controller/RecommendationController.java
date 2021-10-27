package com.example.portfolioservice.controller;

import com.example.portfolioservice.model.PortfolioDto;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;

@RestController
public class RecommendationController {

	@Autowired
	RecommendationService recommendationService;


	@ApiOperation(value = "포트폴리오 추천", notes = "특정 유저의 정보로 포트폴리오를 추천하여 조회 결과를 반환")
	@ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
					dataType = "long", defaultValue = "None")
	@GetMapping(value = "/recommendation/portfolio/{userId}")
	public ResponseEntity<EntityModel<PortfolioDto>> getRecommendationPortfolio(@PathVariable("userId") long userId) throws IOException, ParseException {
		PortfolioDto portfolio = recommendationService.recommendByPortfolio(userId);
		
		return ResponseEntity.ok().body(
				EntityModel.of(portfolio).add(linkTo(methodOn(RecommendationController.class).getRecommendationPortfolio(userId)).withSelfRel())
		);
	}
}
