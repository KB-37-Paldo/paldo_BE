package com.example.portfolioservice.controller;

import com.example.portfolioservice.form.PortfolioForm;
import com.example.portfolioservice.model.PortfolioResponseDto;
import com.example.portfolioservice.service.PortfolioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Api(value = "Portfolio Service")
@RestController
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @ApiOperation(value = "포트폴리오 조회", notes = "특정 유저의 포트폴리오 정보를 반환")
    @GetMapping(value = "/portfolio/{userId}")
    public ResponseEntity<EntityModel<PortfolioResponseDto>> getUserPortfolio(@PathVariable("userId") long userId) {
        PortfolioResponseDto portfolio = portfolioService.findByUserId(userId);

        return ResponseEntity.ok().body(EntityModel.of(portfolio)
                .add(linkTo(methodOn(PortfolioController.class).getUserPortfolio(userId)).withSelfRel())
                .add(linkTo(methodOn(PortfolioController.class).getUserPortfolio(userId)).withRel("portfolioUpdate"))
                .add(linkTo(methodOn(PortfolioController.class).deletePortfolio(userId)).withRel("portfolioDelete"))
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
    public ResponseEntity<Long> putPortfolio(@PathVariable("id") long user_id,
                                             @RequestBody @Valid PortfolioForm portfolioForm) {
        portfolioForm.setUser_id(user_id);
        portfolioService.updatePortfolio(portfolioForm);
        return ResponseEntity.ok().build();
    }
    
    // 자산 조회 
    @GetMapping(value = "/{userId}/asset")
    public void getAsset(){
        portfolioService.getAsset();
    }

}
