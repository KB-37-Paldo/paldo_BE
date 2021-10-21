package com.example.portfolioservice.controller;

import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    // 포트폴리오 조회
    @GetMapping(value = "/{id}/portfolio")
    public ResponseEntity<EntityModel<PortfolioDto>> getUserPortfolio(@PathVariable("id") long user_id) {
        PortfolioDto portfolio = portfolioService.findByUserId(user_id);

        return ResponseEntity.ok().body(
                EntityModel.of(portfolio)
                    .add(linkTo(methodOn(PortfolioController.class).getUserPortfolio(user_id)).withSelfRel())
        );
    }

    // 포트폴리오 전체 조회
    @GetMapping(value = "/portfolios")
    public EntityModel<List<PortfolioDto>> getPortfolios() {
        EntityModel<List<PortfolioDto>> entityModel = EntityModel.of(portfolioService.findAll());
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPortfolios());
        entityModel.add(linkBuilder.withRel("test"));
        return entityModel;
    }
    
    
    // 포트폴리오 생성 
    @PostMapping(value = "/{userId}/portfolio")
    public void createPortfolios() {
    	
    	//TODO 파라메터 넘겨준거 자바에 담기 - 시은 
        portfolioService.createPortfolio();
    }
    
    // 자산 조회 
    @GetMapping(value = "/{userId}/asset")
    public void getAsset(){
        portfolioService.getAsset();
    }
    
}
