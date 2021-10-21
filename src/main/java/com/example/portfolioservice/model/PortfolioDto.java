package com.example.portfolioservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor
public class PortfolioDto {
    private Long id;
    private InvestTypeEnum invest_type;
    private Long target_price;
    private Date target_period;
    private Date create_at;
    private Long user_id;
}
