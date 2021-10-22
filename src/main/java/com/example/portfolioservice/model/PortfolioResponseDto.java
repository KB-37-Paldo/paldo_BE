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
    private long portfolio_id;
    private int invest_type;
    private long target_price;
    private Date target_period;
    private Date create_at;
    private long user_id;
    private int cashAmount;
    private int stockAmount;
    private int goldAmount;
    private int bondAmount;
    private int fundAmount;
    private int realEstateAmount;

    public PortfolioResponseDto(PortfolioDto portfolioDto) {
        this.portfolio_id = portfolioDto.getPortfolio_id();
        this.invest_type = portfolioDto.getInvest_type();
        this.target_price = portfolioDto.getTarget_price();
        this.target_period = portfolioDto.getTarget_period();
        this.create_at = portfolioDto.getCreate_at();
        this.user_id = portfolioDto.getUser_id();
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
