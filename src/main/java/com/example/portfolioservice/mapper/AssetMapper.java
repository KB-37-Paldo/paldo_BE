package com.example.portfolioservice.mapper;

import com.example.portfolioservice.model.HoldingsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssetMapper {
    List<HoldingsDto> getAsset(long userId);
}
