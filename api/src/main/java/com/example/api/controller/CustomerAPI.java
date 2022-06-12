package com.example.api.controller;

import com.example.common.dto.CustomerDto;
import com.example.common.dto.PurchasingAmountDto;
import com.example.common.exception.BaseException;
import com.example.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "customers")
public class CustomerAPI {
    private static final Logger log = LoggerFactory.getLogger(CustomerAPI.class);
    private final CustomerService customerService;

    public CustomerAPI(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<List<CustomerDto>> listAll()
    {
        return new ResponseEntity<>(this.customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{customer-id}")
    @Transactional(readOnly = true)
    public ResponseEntity<CustomerDto> getById(@PathVariable("customer-id") Long customerId)
            throws BaseException
    {
        CustomerDto customerDTO = this.customerService.findById(customerId);
        log.info("GET CUSTOMER- customer found with id {}: {}", customerId, customerDTO);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
    @GetMapping(value = "{customer-id}/purchasing-order/{order-id}")
    @Transactional(readOnly = true)
    public ResponseEntity<PurchasingAmountDto> getAmountDetail(@PathVariable("customer-id") Long customerId, @PathVariable("order-id") Long orderId)
            throws BaseException
    {
        PurchasingAmountDto purchasingAmountDTO = this.customerService.getPurshasedAmount(customerId, orderId);
        log.info("GET PURCHASE AMOUNT DETAIL-  detail found {}", purchasingAmountDTO);
        return new ResponseEntity<>(purchasingAmountDTO, HttpStatus.OK);
    }
}
