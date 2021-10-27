package com.example.portfolioservice.mapper;

import com.example.portfolioservice.model.HoldingsDto;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface AssetMapper {
    List<HoldingsDto> getAsset(long userId);
    List<Map<String, BigDecimal>> getAiData(long userId);
}
