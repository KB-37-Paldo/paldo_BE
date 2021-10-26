package com.example.portfolioservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class HoldingsResponseDto {
    private long targetPrice = 0;
    private Date targetDate = null;
    private long totAmount = 0;
    private HoldingsDetailDto cash = new HoldingsDetailDto();
    private HoldingsDetailDto stock = new HoldingsDetailDto();
    private HoldingsDetailDto realAssets = new HoldingsDetailDto();
    private HoldingsDetailDto bond = new HoldingsDetailDto();
    private HoldingsDetailDto fund = new HoldingsDetailDto();
    private HoldingsDetailDto realEstate = new HoldingsDetailDto();

    public void setAmount() {
        this.totAmount = 0;
        this.totAmount = this.cash.setTotAmount();
        this.totAmount = this.stock.setTotAmount();
        this.totAmount = this.realAssets.setTotAmount();
        this.totAmount = this.bond.setTotAmount();
        this.totAmount = this.fund.setTotAmount();
        this.totAmount = this.realEstate.setTotAmount();
    }

}
