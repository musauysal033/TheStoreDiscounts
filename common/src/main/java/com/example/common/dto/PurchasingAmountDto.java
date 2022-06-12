package com.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchasingAmountDto {

    BigDecimal totalPurchaseAmount;
    BigDecimal totalDiscountablePurchaseAmount;
    Integer    discountPercentage;
    BigDecimal discountPercentageAmount;
    BigDecimal discountHundredAmount;
    BigDecimal totalNetPurchaseAmount;
}
