package com.example.portfolioservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDetailDto {
    private long portfolioDetailId;
    private int percentage;
    private String type;
}
