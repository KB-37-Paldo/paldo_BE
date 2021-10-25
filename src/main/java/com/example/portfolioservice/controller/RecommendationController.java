package com.example.portfolioservice.controller;

import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.service.RecommendationService;

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
	
	@GetMapping(value = "/recommendation/portfolio/{id}")
	public ResponseEntity<EntityModel<PortfolioDto>> getRecommendationPortfolio(@PathVariable("id") long user_id) throws IOException, ParseException {
		PortfolioDto portfolio = recommendationService.recommendByPortfolio(user_id);
		
		return ResponseEntity.ok().body(
				EntityModel.of(portfolio).add(linkTo(methodOn(RecommendationController.class).getRecommendationPortfolio(user_id)).withSelfRel())
		);
	}
}
