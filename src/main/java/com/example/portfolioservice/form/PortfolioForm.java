package com.example.portfolioservice.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioForm {

    private long userId;

    @Min(0)
    private long targetPrice;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date targetPeriod;

    @Min(0)
    private int cashAmount;

    @Min(0)
    private int stockAmount;

    @Min(0)
    private int goldAmount;

    @Min(0)
    private int bondAmount;

    @Min(0)
    private int fundAmount;

    @Min(0)
    private int realEstateAmount;
}
