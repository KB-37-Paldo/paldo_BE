package com.example.portfolioservice.controller;

import com.example.portfolioservice.form.PortfolioForm;
import com.example.portfolioservice.model.PortfolioResponseDto;
import com.example.portfolioservice.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    // 포트폴리오 조회
    @GetMapping(value = "/portfolio/{id}")
    public ResponseEntity<EntityModel<PortfolioResponseDto>> getUserPortfolio(@PathVariable("id") long user_id) {
        PortfolioResponseDto portfolio = portfolioService.findByUserId(user_id);

        return ResponseEntity.ok().body(EntityModel.of(portfolio)
                .add(linkTo(methodOn(PortfolioController.class).getUserPortfolio(user_id)).withSelfRel())
                .add(linkTo(methodOn(PortfolioController.class).getUserPortfolio(user_id)).withRel("portfolioUpdate"))
                .add(linkTo(methodOn(PortfolioController.class).deletePortfolio(user_id)).withRel("portfolioDelete"))
        );
    }

    // 포트폴리오 삭제
    @DeleteMapping(value = "/{id}/portfolio")
    public ResponseEntity<Long> deletePortfolio(@PathVariable("id") long user_id) {
        return ResponseEntity.ok().body(portfolioService.deleteByUserId(user_id));
    }

    // 포트폴리오 생성 
    @PostMapping(value = "/{userId}/portfolio")
    public void createPortfolios() {
    	
    	//TODO 파라메터 넘겨준거 자바에 담기 - 시은 
        portfolioService.createPortfolio();
    }

    // 포트폴리오 수정
    @PutMapping(value = "/{userId}/portfolio")
    public ResponseEntity<Long> putPortfolio(@PathVariable("userId") long userId,
                                             @RequestBody @Valid PortfolioForm portfolioForm) {
        portfolioForm.setUserId(userId);
        portfolioService.updatePortfolio(portfolioForm);
        return ResponseEntity.ok().build();
    }
    
    // 자산 조회 
    @GetMapping(value = "/{userId}/asset")
    public void getAsset(){
        portfolioService.getAsset();
    }

}
