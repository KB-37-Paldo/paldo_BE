package com.example.portfolioservice.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum InvestType {
    Type1("공격투자형", -3, 2, 1, 4, -1, -3),
    Type2("적극투자형", -1, -1, 2, 3, -2, -1),
    Type3("위험중립형", 1, -4, 3, 2, -3, 1),
    Type4("안정추구형", 3, -7, 4, 1, -4, 3),
    Type5("안정형", 5, -10, 5, 0, -5, 5);

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
