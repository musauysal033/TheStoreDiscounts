package com.example.service;

import com.example.common.dto.CustomerDto;
import com.example.common.dto.PurchasingAmountDto;
import com.example.common.enumeration.ItemType;
import com.example.entity.Item;
import com.example.service.rule.engine.RulesDiscountEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Service
public class PurchaseAmountService {
    private final Logger log = LoggerFactory.getLogger(PurchaseAmountService.class);
    private static final BigDecimal TWO_HUNDRED = BigDecimal.valueOf(200);
    private static final BigDecimal SPENDING_OVER_200 = BigDecimal.valueOf(5);
    private final RulesDiscountEvaluator rulesEvaluator;

    public PurchaseAmountService(RulesDiscountEvaluator rulesEvaluator) {
        this.rulesEvaluator = rulesEvaluator;
    }

    public PurchasingAmountDto calculateNetTotalAmount(CustomerDto customer, Set<Item> purchasedItems)
    {
        BigDecimal totalPurchaseAmount = this.calculateTotalAmount(purchasedItems);
        log.debug("calculate Net Total Amount, totalPurchaseAmount {}", totalPurchaseAmount);
        BigDecimal totalDiscountablePurchaseAmount = this.calculateTotalDiscountableAmount(purchasedItems);
        log.debug("calculate Net Total Amount, totalDiscountablePurchaseAmount {}", totalDiscountablePurchaseAmount);
        Integer    discountPercentage = Integer.valueOf(0);
        BigDecimal discountPercentageAmount = BigDecimal.ZERO;
        BigDecimal discountHundredAmount =  BigDecimal.ZERO;

        if(!BigDecimal.ZERO.equals(totalDiscountablePurchaseAmount))
        {
            discountPercentage = this.rulesEvaluator.calculateDiscountPercentage(customer);
            log.debug("calculate Net Total Amount, discountPercentage {}", discountPercentage);
            discountPercentageAmount = this.calculatePercentage(discountPercentage,totalDiscountablePurchaseAmount);
            log.debug("calculate Net Total Amount, discountPercentageAmount {}", discountPercentageAmount);
            discountHundredAmount  = totalPurchaseAmount.compareTo(TWO_HUNDRED) > 0
                    ?totalPurchaseAmount.divide(TWO_HUNDRED, 0, RoundingMode.DOWN).multiply(SPENDING_OVER_200)
                    :BigDecimal.ZERO;
            log.debug("calculate Net Total Amount, discountHundredAmount {}", discountHundredAmount);
        }
        else
        {
            log.info("Total discountable items amount is 0");
        }

        BigDecimal totalNetPurchaseAmount = totalPurchaseAmount.subtract(discountPercentageAmount).subtract(discountHundredAmount);
        log.debug("calculate Net Total Amount, totalNetPurchaseAmount {}", totalNetPurchaseAmount);
        return new PurchasingAmountDto( totalPurchaseAmount,
                totalDiscountablePurchaseAmount,
                discountPercentage,
                discountPercentageAmount,
                discountHundredAmount,
                totalNetPurchaseAmount
        );

    }
    private BigDecimal calculateTotalAmount(Set<Item> purchasedItems)
    {
        return  purchasedItems.stream().map(Item::getItemPrice).
                reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));

    }

    /**
     * Calculate total items amount that discount apply as a product
     * @param purchasedItems
     * @return
     */
    private BigDecimal calculateTotalDiscountableAmount(Set<Item> purchasedItems)
    {
        return purchasedItems.stream()
                .filter(item -> !item.getItemType().equals(ItemType.GROCERY))
                .map(Item::getItemPrice)
                .reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
    }

    /**
     * Calculate percentage
     * @param percent
     * @param total
     * @return
     */
    private BigDecimal calculatePercentage(Integer percent, BigDecimal total)
    {
        return total.multiply(BigDecimal.valueOf(percent)).divide(TWO_HUNDRED, 10, RoundingMode.FLOOR);
    }
}
