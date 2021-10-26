package com.example.portfolioservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HoldingsResponseDto {
    private long targetPrice = 0;
    private Date targetDate = null;
    private long totAmount = 0;
    private float cashAmount;
    private float stockAmount;
    private float realAssetsAmount;
    private float bondAmount;
    private float fundAmount;
    private float realEstateAmount;
    private HoldingsDetailDto cash = new HoldingsDetailDto();
    private HoldingsDetailDto stock = new HoldingsDetailDto();
    private HoldingsDetailDto realAssets = new HoldingsDetailDto();
    private HoldingsDetailDto bond = new HoldingsDetailDto();
    private HoldingsDetailDto fund = new HoldingsDetailDto();
    private HoldingsDetailDto realEstate = new HoldingsDetailDto();

    public void setAmount() {
        this.totAmount = 0;
        this.totAmount += this.cash.setTotAmount();
        this.totAmount += this.stock.setTotAmount();
        this.totAmount += this.realAssets.setTotAmount();
        this.totAmount += this.bond.setTotAmount();
        this.totAmount += this.fund.setTotAmount();
        this.totAmount += this.realEstate.setTotAmount();
        this.cashAmount = Math.round(((float) this.cash.getTotAmount() / this.totAmount)*1000)/10.0f;
        this.stockAmount = Math.round(((float) this.stock.getTotAmount() / this.totAmount)*1000)/10.0f;
        this.realAssetsAmount = Math.round(((float) this.realAssets.getTotAmount() / this.totAmount)*1000)/10.0f;
        this.bondAmount = Math.round(((float) this.bond.getTotAmount() / this.totAmount)*1000)/10.0f;
        this.fundAmount = Math.round(((float) this.fund.getTotAmount() / this.totAmount)*1000)/10.0f;
        this.realEstateAmount = Math.round(((float) this.realEstate.getTotAmount() / this.totAmount)*1000)/10.0f;
    }

}
