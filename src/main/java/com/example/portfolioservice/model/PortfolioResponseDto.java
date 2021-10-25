package com.example.portfolioservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PortfolioResponseDto {
    private long portfolioId;
    private String investType;
    private long targetPrice;
    private Date targetPeriod;
    private Date createAt;
    private long userId;
    private int cashAmount;
    private int stockAmount;
    private int goldAmount;
    private int bondAmount;
    private int fundAmount;
    private int realEstateAmount;

    public PortfolioResponseDto(PortfolioDto portfolioDto) {
        this.portfolioId = portfolioDto.getPortfolioId();
        this.investType = portfolioDto.getInvestType();
        this.targetPrice = portfolioDto.getTargetPrice();
        this.targetPeriod = portfolioDto.getTargetPeriod();
        this.createAt = portfolioDto.getCreateAt();
        this.userId = portfolioDto.getUserId();
        this.setAmount(portfolioDto.getDetails());
    }

    public void setAmount(List<PortfolioDetailDto> details) {
        for (PortfolioDetailDto detail : details) {
            if (detail.getType().equals(AssetType.cash.getTitle())) {
                this.cashAmount = detail.getPercentage();
            }
            else if (detail.getType().equals(AssetType.stock.getTitle())) {
                this.stockAmount = detail.getPercentage();
            }
            else if (detail.getType().equals(AssetType.gold.getTitle())) {
                this.goldAmount = detail.getPercentage();
            }
            else if (detail.getType().equals(AssetType.bond.getTitle())) {
                this.bondAmount = detail.getPercentage();
            }
            else if (detail.getType().equals(AssetType.fund.getTitle())) {
                this.fundAmount = detail.getPercentage();
            }
            else if (detail.getType().equals(AssetType.realEstate.getTitle())) {
                this.realEstateAmount = detail.getPercentage();
            }
        }
    }
}
