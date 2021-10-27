package com.example.portfolioservice.mapper;

import com.example.portfolioservice.model.ProductDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
	List<ProductDto> getProduct(Map<String, Object> mapParam );
}
