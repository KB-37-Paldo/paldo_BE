package com.example.portfolioservice.controller;

import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.model.UserDto;
import com.example.portfolioservice.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    // 포트폴리오 조회
    @GetMapping(value = "/{id}/portfolio")
    public ResponseEntity<Object> getUserPortfolio(@PathVariable("id") long user_id) {
        PortfolioDto portfolioDto = portfolioService.findByUserID(user_id);
        return ResponseEntity.ok(portfolioDto);
    }

    // test
    @GetMapping(value = "/users")
    public ResponseEntity<Object> getUserAll() {
        List<UserDto> users = portfolioService.findAll();
        return ResponseEntity.ok(users);
    }

    // test
    @GetMapping(value = "/test")
    public ResponseEntity<Object> getAll() {
        List<PortfolioDto> portfolioDtos = portfolioService.findPortfolioAll();
        return ResponseEntity.ok(portfolioDtos);
    }

}
