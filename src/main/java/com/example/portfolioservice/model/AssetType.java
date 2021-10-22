package com.example.portfolioservice.model;

import lombok.Getter;

public enum AssetType {
    cash("현금성"),
    stock("주식"),
    gold("금"),
    bond("체권"),
    fund("펀드"),
    realEstate("부동산");

    @Getter
    private final String title;

    AssetType(String title) {
        this.title = title;
    }


}
