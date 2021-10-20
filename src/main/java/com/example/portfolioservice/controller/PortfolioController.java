package com.example.portfolioservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortfolioController {

    // 포트폴리오 조회
    @GetMapping(value = "/{id}/portfolio")
    public ResponseEntity<Object> getUserPortfolio(@PathVariable("id") Long user_id) {
        return ResponseEntity.ok().build();
    }

}
