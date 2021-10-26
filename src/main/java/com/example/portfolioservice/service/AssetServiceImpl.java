package com.example.portfolioservice.service;

import com.example.portfolioservice.mapper.AssetMapper;
import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.model.AssetType;
import com.example.portfolioservice.model.HoldingsDto;
import com.example.portfolioservice.model.HoldingsResponseDto;
import com.example.portfolioservice.model.PortfolioDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService{

    @Autowired
    SqlSession sqlsession;

    @Override
    public HoldingsResponseDto getAsset(long userId) {
        List<HoldingsDto> holdings = sqlsession.getMapper(AssetMapper.class).getAsset(userId);
        Optional<PortfolioDto> portfolio = sqlsession.getMapper(PortfolioMapper.class).findByUserId(userId);
        HoldingsResponseDto response = new HoldingsResponseDto();
        if (portfolio.isPresent()) {
            response.setTargetPrice(portfolio.get().getTargetPrice());
            response.setTargetDate(portfolio.get().getTargetPeriod());
        }
        for (HoldingsDto holding : holdings) {
            if (holding.getAssetType().equals(AssetType.cash.getTitle())) {
                response.getCash().add(holding);
                response.setCashAmount(response.getCashAmount()+holding.getAmount());
            }else if (holding.getAssetType().equals(AssetType.stock.getTitle())) {
                response.getStock().add(holding);
                response.setStockAmount(response.getCashAmount()+holding.getAmount());
            }else if (holding.getAssetType().equals(AssetType.gold.getTitle())) {
                response.getGold().add(holding);
                response.setGoldAmount(response.getGoldAmount()+holding.getAmount());
            }else if (holding.getAssetType().equals(AssetType.bond.getTitle())) {
                response.getBond().add(holding);
                response.setBondAmount(response.getBondAmount()+holding.getAmount());
            }else if (holding.getAssetType().equals(AssetType.fund.getTitle())) {
                response.getFund().add(holding);
                response.setFundAmount(response.getFundAmount()+holding.getAmount());
            }else if (holding.getAssetType().equals(AssetType.realEstate.getTitle())) {
                response.getRealEstate().add(holding);
                response.setRealEstateAmount(response.getRealEstateAmount()+holding.getAmount());
            }
        }
        response.setTotalAmount();
        return response;
    }
}
