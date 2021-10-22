package com.example.portfolioservice.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
public class PortfolioForm {

    private long user_id;

    @Min(0) @NotBlank
    private long target_price;

    @NotBlank
    private Date target_period;

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
