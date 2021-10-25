package com.example.portfolioservice.service;

import com.example.portfolioservice.mapper.AssetMapper;
import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.model.HoldingsDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService{

    @Autowired
    SqlSession sqlsession;

    @Override
    public List<HoldingsDto> getAsset(HoldingsDto vo) {
        List<HoldingsDto> list = sqlsession.getMapper(AssetMapper.class).getAsset(vo);
        return list;
    }
}
