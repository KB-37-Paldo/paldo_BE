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
                response.getCash().getData().add(holding);
            }else if (holding.getAssetType().equals(AssetType.stock.getTitle())) {
                response.getStock().getData().add(holding);
            }else if (holding.getAssetType().equals(AssetType.gold.getTitle())) {
                response.getRealAssets().getData().add(holding);
            }else if (holding.getAssetType().equals(AssetType.bond.getTitle())) {
                response.getBond().getData().add(holding);
            }else if (holding.getAssetType().equals(AssetType.fund.getTitle())) {
                response.getFund().getData().add(holding);
            }else if (holding.getAssetType().equals(AssetType.realEstate.getTitle())) {
                response.getRealEstate().getData().add(holding);
            }
        }
        response.setAmount();
        return response;
    }
}
