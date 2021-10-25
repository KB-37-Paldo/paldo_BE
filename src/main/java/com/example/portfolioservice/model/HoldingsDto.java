package com.example.portfolioservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoldingsDto {
    private int holdings_id;
    private long amount;
    private String detail_type;
    private long user_id;
    private long asset_type_id;
}
