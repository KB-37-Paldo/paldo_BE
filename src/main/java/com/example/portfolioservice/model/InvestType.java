package com.example.portfolioservice.model;

import io.swagger.models.auth.In;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum InvestType {
    Type1("공격투자형", 50, 20, 10, 10, 5, 5),
    Type2("적극투자형", 45, 20, 15, 15, 10, 5),
    Type3("위험중립형", 30, 15, 10, 15, 15, 15),
    Type4("안정추구형", 15, 15, 15, 15, 20, 20),
    Type5("안정형", 10, 15, 10, 15, 20, 30);

    private final String title;
    private final int cash;
    private final int stock;
    private final int gold;
    private final int bond;
    private final int fund;
    private final int realEstate;

    InvestType(String title, int cash, int stock, int gold, int bond, int fund, int realEstate) {
        this.title = title;
        this.cash = cash;
        this.stock = stock;
        this.gold = gold;
        this.bond = bond;
        this.fund = fund;
        this.realEstate = realEstate;
    }

    static public InvestType valueOfTitle(String title) {
        return Arrays.stream(InvestType.values())
                .filter((invest)->title.equals(invest.getTitle()))
                .findAny().get();
    }

}
