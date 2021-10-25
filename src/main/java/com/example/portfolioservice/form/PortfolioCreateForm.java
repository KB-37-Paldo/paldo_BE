package com.example.portfolioservice.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.sql.Date;

@Getter
@Setter
public class PortfolioCreateForm {

    private long userId;

    private long portfolioId;

    private String investType;

    @Min(0)
    private long targetPrice;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date targetPeriod;
}
