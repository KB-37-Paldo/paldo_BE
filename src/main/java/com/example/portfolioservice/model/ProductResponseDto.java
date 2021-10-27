package com.example.portfolioservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProductResponseDto {
	private List<ProductDto> cash = new ArrayList<>();
	private List<ProductDto> invest = new ArrayList<>(); 
	
}
