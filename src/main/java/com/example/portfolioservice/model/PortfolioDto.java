package com.example.portfolioservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDto {
    private long portfolio_id;
    private String invest_type;
    private long target_price;
    private Date target_period;
    private Date create_at;
    private long user_id;
    private List<PortfolioDetailDto> details;
}
