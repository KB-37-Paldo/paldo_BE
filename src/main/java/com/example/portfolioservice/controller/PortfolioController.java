package com.example.portfolioservice.controller;

import com.example.portfolioservice.form.PortfolioCreateForm;
import com.example.portfolioservice.form.PortfolioUpdateForm;
import com.example.portfolioservice.model.PortfolioResponseDto;
import com.example.portfolioservice.service.PortfolioService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Api(value = "Portfolio Service")
@RestController
@RequestMapping(value = "/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;


    @ApiOperation(value = "포트폴리오 조회", notes = "특정 유저의 포트폴리오 정보를 반환")
    @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
            dataType = "long", defaultValue = "None")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<EntityModel<PortfolioResponseDto>> getUserPortfolio(@PathVariable("userId") long userId) {
        PortfolioResponseDto portfolio = portfolioService.findByUserId(userId);

        WebMvcLinkBuilder portfolioLink= linkTo(PortfolioController.class).slash(userId);

        return ResponseEntity.ok().body(EntityModel.of(portfolio)
                .add(portfolioLink.withSelfRel())
                .add(portfolioLink.withRel("portfolioUpdate"))
                .add(portfolioLink.withRel("portfolioDelete"))
        );
    }


    @ApiOperation(value = "포트폴리오 삭제", notes = "특정 유저의 포트폴리오 정보를 삭제")
    @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
            dataType = "long", defaultValue = "None")
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Long> deletePortfolio(@PathVariable("userId") long userId) {
        return ResponseEntity.ok().body(portfolioService.deleteByUserId(userId));
    }


    @ApiOperation(value = "포트폴리오 생성", notes = "특정 유저의 포트폴리오 생성 정보를 전달받아 포트폴리오 정보를 생성하고 포트폴리오 아이디를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
                    dataType = "long", defaultValue = "None"),
            @ApiImplicitParam(name = "portfolioCreateForm", value = "포트폴리오 생성 정보", required = true,
                    dataType = "PortfolioCreateForm", defaultValue = "None")})
    @PostMapping(value = "/{userId}")
    public ResponseEntity<Long> createPortfolios(@PathVariable("userId") long userId,
                                                 @RequestBody @Valid PortfolioCreateForm portfolioCreateForm) {
        portfolioCreateForm.setUserId(userId);
        long portfolioId = portfolioService.createPortfolio(portfolioCreateForm);
        return new ResponseEntity<Long>(portfolioId, HttpStatus.CREATED);
    }


    @ApiOperation(value = "포트폴리오 수정", notes = "특정 유저의 포트폴리오 수정 정보를 전달받아 포트폴리오 정보를 수정하고 포트폴리오 아이디를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
                    dataType = "long", defaultValue = "None"),
            @ApiImplicitParam(name = "portfolioUpdateForm", value = "포트폴리오 수정 정보", required = true,
                    dataType = "PortfolioUpdateForm", defaultValue = "None")})
    @PutMapping(value = "/{userId}")
    public ResponseEntity<Long> putPortfolio(@PathVariable("userId") long userId,
                                             @RequestBody @Valid PortfolioUpdateForm portfolioUpdateForm) {
        portfolioUpdateForm.setUserId(userId);
        long portfolioId = portfolioService.updatePortfolio(portfolioUpdateForm);
        return ResponseEntity.ok().body(portfolioId);
    }
}
