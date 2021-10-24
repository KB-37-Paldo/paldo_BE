package com.example.portfolioservice.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PortfolioDetailForm {
    private long portfolioId;
    private long typeId;
    private int percentage;
}
