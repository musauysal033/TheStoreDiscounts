package com.example.common.enumeration;

public enum DiscountType {
    EMPLOYEE_DISCOUNT(30),
    AFFILIATE_DISCOUNT(10),
    CUSTOMER_OVER_TWO_YEARS(5);

    private final int percentage;

    DiscountType(int percentage)
    {
        this.percentage = percentage;
    }

    public int getPercentage()
    {
        return percentage;
    }
}
