package com.example.portfolioservice.controller;

import com.example.portfolioservice.form.PortfolioCreateForm;
import com.example.portfolioservice.form.PortfolioUpdateForm;
import com.example.portfolioservice.model.PortfolioResponseDto;
import com.example.portfolioservice.service.PortfolioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Api(value = "Portfolio Service")
@RestController
@RequestMapping(value = "/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @ApiOperation(value = "포트폴리오 조회", notes = "특정 유저의 포트폴리오 정보를 반환")
    @GetMapping(value = "/portfolio/{userId}")
    public ResponseEntity<EntityModel<PortfolioResponseDto>> getUserPortfolio(@PathVariable("userId") long userId) {
        PortfolioResponseDto portfolio = portfolioService.findByUserId(userId);

        WebMvcLinkBuilder portfolioLink= linkTo(PortfolioController.class).slash(userId);

        return ResponseEntity.ok().body(EntityModel.of(portfolio)
                .add(portfolioLink.withSelfRel())
                .add(portfolioLink.withRel("portfolioUpdate"))
                .add(portfolioLink.withRel("portfolioDelete"))
        );
    }

    // 포트폴리오 삭제
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Long> deletePortfolio(@PathVariable("userId") long userId) {
        return ResponseEntity.ok().body(portfolioService.deleteByUserId(userId));
    }

    // 포트폴리오 생성 
    @PostMapping(value = "/{userId}")
    public ResponseEntity<Long> createPortfolios(@PathVariable("userId") long userId,
                                                 @RequestBody @Valid PortfolioCreateForm portfolioCreateForm) {
        portfolioCreateForm.setUserId(userId);
        portfolioService.createPortfolio(portfolioCreateForm);
        return null;
    }

    // 포트폴리오 수정
    @PutMapping(value = "/{userId}")
    public ResponseEntity<Long> putPortfolio(@PathVariable("userId") long userId,
                                             @RequestBody @Valid PortfolioUpdateForm portfolioUpdateForm) {
        portfolioUpdateForm.setUserId(userId);
        portfolioService.updatePortfolio(portfolioUpdateForm);
        return ResponseEntity.ok().build();
    }


}
