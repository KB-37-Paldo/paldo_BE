package com.example.portfolioservice.model;

import lombok.Getter;

@Getter
public enum AssetType {
    cash(1, "현금성"),
    stock(2, "주식"),
    gold(3, "금"),
    bond(4, "채권"),
    fund(5, "펀드"),
    realEstate(6,"부동산");

    private final int typeId;
    private final String title;

    AssetType(int typeId, String title) {
        this.typeId = typeId;
        this.title = title;
    }


}
