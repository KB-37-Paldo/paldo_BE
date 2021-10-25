package com.example.portfolioservice.service;

import com.example.portfolioservice.model.HoldingsDto;

import java.util.List;

public interface AssetService {
    // 자산조회
    List<HoldingsDto> getAsset(HoldingsDto vo);

}
