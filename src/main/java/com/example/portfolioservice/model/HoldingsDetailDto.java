package com.example.portfolioservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HoldingsDetailDto {
    private long totAmount;
    private List<HoldingsDto> data = new ArrayList<>();

    public long setTotAmount() {
        this.totAmount = 0;
        for (HoldingsDto holding : data) {
            this.totAmount += holding.getAmount();
        }
        return this.totAmount;
    }
}
