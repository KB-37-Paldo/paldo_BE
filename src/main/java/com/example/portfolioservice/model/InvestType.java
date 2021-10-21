package com.example.portfolioservice.model;

import lombok.Getter;


public enum InvestType {
    Type1("공격투자형"),
    Type2("적극투자형"),
    Type3("위험중립형"),
    Type4("안정추구형"),
    Type5("안정형");

    private final String title;

    InvestType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
