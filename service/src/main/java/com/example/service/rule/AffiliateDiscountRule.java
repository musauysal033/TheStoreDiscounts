package com.example.service.rule;

import com.example.common.dto.CustomerDto;

import com.example.common.enumeration.CustomerType;
import com.example.common.enumeration.DiscountType;

public class AffiliateDiscountRule implements DiscountRule{
    @Override
    public Integer calculateCustomerDiscount(CustomerDto customerDto) {
        return CustomerType.AFFILIATE.equals(customerDto.getCustomerType())?
                DiscountType.AFFILIATE_DISCOUNT.getPercentage() : 0;
    }
}
