package com.example.service.rule.engine;

import com.example.common.dto.CustomerDto;
import com.example.service.rule.AffiliateDiscountRule;
import com.example.service.rule.DiscountRule;
import com.example.service.rule.EmployeeDiscountRule;
import com.example.service.rule.TwoYearsDiscountRule;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class RulesDiscountEvaluator {

    private Set<DiscountRule> rules;
    @PostConstruct
    public void setRulesDictionary(){
        this.rules = new LinkedHashSet<>();
        this.rules.add(new EmployeeDiscountRule());
        this.rules.add(new AffiliateDiscountRule());
        this.rules.add(new TwoYearsDiscountRule());
    }
    public Integer calculateDiscountPercentage(CustomerDto customer)
    {
        Integer percentage = 0;
        for(DiscountRule rule : this.rules)
        {
            percentage = Math.max(rule.calculateCustomerDiscount(customer),percentage);
        }
        return percentage;
    }
}
