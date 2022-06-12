package com.example.service.rule;

import com.example.common.dto.CustomerDto;

public interface DiscountRule {
    Integer calculateCustomerDiscount(CustomerDto customerDto);
}
