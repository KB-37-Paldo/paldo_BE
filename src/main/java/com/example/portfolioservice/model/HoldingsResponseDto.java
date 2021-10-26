package com.example.portfolioservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class HoldingsResponseDto {
    private long targetPrice;
    private Date targetDate;
    private long totalAmount;
    private long cashAmount;
    private long stockAmount;
    private long goldAmount;
    private long bondAmount;
    private long fundAmount;
    private long realEstateAmount;
    private List<HoldingsDto> cash = new ArrayList<HoldingsDto>();
    private List<HoldingsDto> stock = new ArrayList<HoldingsDto>();
    private List<HoldingsDto> gold = new ArrayList<HoldingsDto>();
    private List<HoldingsDto> bond = new ArrayList<HoldingsDto>();
    private List<HoldingsDto> fund = new ArrayList<HoldingsDto>();
    private List<HoldingsDto> realEstate = new ArrayList<HoldingsDto>();

    public void setTotalAmount() {
        this.totalAmount = this.cashAmount + this.stockAmount + this.goldAmount + this.bondAmount
                + this.fundAmount + this.realEstateAmount;
    }
}
