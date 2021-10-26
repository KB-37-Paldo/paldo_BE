package com.example.portfolioservice.service;

import com.example.portfolioservice.model.HoldingsResponseDto;

public interface AssetService {
    // 자산조회
    HoldingsResponseDto getAsset(long userId);

}
