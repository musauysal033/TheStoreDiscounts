package com.example.service.rule;

import com.example.common.dto.CustomerDto;
import com.example.common.enumeration.DiscountType;


import java.time.LocalDateTime;

public class TwoYearsDiscountRule implements DiscountRule {
    @Override
    public Integer calculateCustomerDiscount(CustomerDto customerDto) {
        return customerDto.getCreatedDate().isBefore(LocalDateTime.now().minusYears(2))?
                DiscountType.CUSTOMER_OVER_TWO_YEARS.getPercentage() : 0;
    }
}
