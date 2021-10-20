package com.example.portfolioservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor
public class PortfolioDto {
    private long id;
    private int invest_type;
    private long target_price;
    private Date target_period;
    private Date create_at;
    private long user_id;
}
