package com.example.service.rule;

import com.example.common.dto.CustomerDto;

import com.example.common.enumeration.CustomerType;
import com.example.common.enumeration.DiscountType;

public class EmployeeDiscountRule implements DiscountRule {
    @Override
    public Integer calculateCustomerDiscount(CustomerDto customerDto) {
        return CustomerType.EMPLOYEE.equals(customerDto.getCustomerType())
                ? DiscountType.EMPLOYEE_DISCOUNT.getPercentage():0;
    }
}
