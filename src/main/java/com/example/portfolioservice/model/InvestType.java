package com.example.portfolioservice.model;

import lombok.Getter;


public enum InvestType { // Todo 용균-유형별로 투자 비율 default값 설정
    Type1("공격투자형"), // ex) 30 20 10 15 10
    Type2("적극투자형"), // ex) 25 25 10 15 10
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
