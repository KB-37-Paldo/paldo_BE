package com.example.portfolioservice.service;


import com.example.portfolioservice.form.PortfolioCreateForm;
import com.example.portfolioservice.form.PortfolioDetailForm;
import com.example.portfolioservice.form.PortfolioUpdateForm;
import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.model.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService{

    @Autowired
    SqlSession sqlsession;

    @Override
    public long deleteByUserId(long userId) {
        return sqlsession.getMapper(PortfolioMapper.class).deleteByUserId(userId);
    }

    @Override
    public PortfolioResponseDto findByUserId(long userId) {
        Optional<PortfolioDto> portfolio = sqlsession.getMapper(PortfolioMapper.class).findByUserId(userId);
        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto(portfolio.get());
        return portfolioResponseDto;
    }

    @Override
    public long createPortfolio(PortfolioCreateForm portfolioCreateForm){
        sqlsession.getMapper(PortfolioMapper.class).createPortfolio(portfolioCreateForm);
        InvestType investType = InvestType.valueOfTitle(portfolioCreateForm.getInvestType());
        long id = portfolioCreateForm.getPortfolioId();
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.cash.getTypeId(), investType.getCash()));
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.stock.getTypeId(), investType.getStock()));
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.gold.getTypeId(), investType.getGold()));
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.bond.getTypeId(), investType.getBond()));
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.fund.getTypeId(), investType.getFund()));
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.realEstate.getTypeId(), investType.getRealEstate()));

        return id;
    }

    @Override
    public long updatePortfolio(PortfolioUpdateForm portfolioUpdateForm) {
        return sqlsession.getMapper(PortfolioMapper.class).updatePortfolio(portfolioUpdateForm);
    }

    @Override
    public boolean exists(long userId) {
        return sqlsession.getMapper(PortfolioMapper.class).exists(userId);
    }

    @Override
    public List<PortfolioResponseDto> findAgePortfolio(int age) {
        return sqlsession.getMapper(PortfolioMapper.class).findByAge((int)(age/10)).stream()
                .map(PortfolioResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PortfolioResponseDto> findAssetPortfolio(long asset) {
        return null;
    }

    @Override
    public List<PortfolioResponseDto> findInvestTypePortfolio(String investType) {
        return null;
    }


}
