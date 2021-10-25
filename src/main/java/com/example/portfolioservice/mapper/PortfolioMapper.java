package com.example.portfolioservice.mapper;

import com.example.portfolioservice.form.PortfolioCreateForm;
import com.example.portfolioservice.form.PortfolioDetailForm;
import com.example.portfolioservice.form.PortfolioUpdateForm;
import com.example.portfolioservice.model.HoldingsDto;
import com.example.portfolioservice.model.PortfolioDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PortfolioMapper {
    Optional<PortfolioDto> findByUserId(long user_id);
    long deleteByUserId(long user_id);
    long createPortfolio(PortfolioCreateForm portfolioCreateForm);
    long updatePortfolio(PortfolioUpdateForm portfolioUpdateForm);
    boolean exists(long userId);
    long createPortfolioDetail(PortfolioDetailForm portfolioDetailForm);
}
