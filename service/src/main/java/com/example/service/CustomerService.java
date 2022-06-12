package com.example.service;

import com.example.common.dto.CustomerDto;
import com.example.common.dto.PurchasingAmountDto;
import com.example.common.exception.BaseException;
import com.example.common.exception.NotFoundException;
import com.example.entity.Customer;
import com.example.entity.PurchaseOrder;
import com.example.repository.CustomerRepository;
import com.example.repository.PurchaseOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private static final String CUSTOMER_DOES_NOT_EXIST = "Customer with id %s does not exist.";
    private static final String PURCHASE_ORDER_DOES_NOT_EXIST = "Purchase order with id %s does not exist.";

    private final PurchaseAmountService purchaseAmountService;
    private final CustomerRepository customerRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    public CustomerService(PurchaseAmountService purchaseAmountService, CustomerRepository customerRepository,PurchaseOrderRepository purchaseOrderRepository)
    {
        this.purchaseAmountService = purchaseAmountService;
        this.customerRepository = customerRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Autowired


    /**
     * Get a list of all Customers
     *
     */
    public List<CustomerDto> findAll()
    {
        return this.customerRepository .findAll()
                .stream()
                .map(customer -> new CustomerDto(customer.getId()
                        , customer.getFirstName()
                        , customer.getLastName()
                        , customer.getCreatedDate(),
                        customer.getCustomerType()))
                .collect(Collectors.toList());
    }

    /**
     * Get the {@link CustomerDto}, by Id
     *
     * @param id
     * @return
     */
    public CustomerDto findById(Long id) throws NotFoundException
    {
        log.debug("Find customer by id {}", id);
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new NotFoundException(String.format(CUSTOMER_DOES_NOT_EXIST, id)));
        return  new CustomerDto(customer.getId()
                ,customer.getFirstName()
                ,customer.getLastName()
                ,customer.getCreatedDate()
                ,customer.getCustomerType());
    }


    public PurchasingAmountDto getPurshasedAmount(long customerId, long purchaseOrderId)
            throws BaseException
    {
        CustomerDto customer = this.findById(customerId);
        PurchaseOrder purchaseOrder = this.purchaseOrderRepository.findById(purchaseOrderId).
                orElseThrow(() -> new NotFoundException(String.format(PURCHASE_ORDER_DOES_NOT_EXIST, purchaseOrderId)));

        log.info("Customer with Id {} was found, Starting Amount discounst calculation.", customerId);
        PurchasingAmountDto purchasingAmountDTO = this.purchaseAmountService.calculateNetTotalAmount(customer,purchaseOrder.getItems());
        log.info("Customer with Id {} have a detailed purchase amount of {}", customerId, purchasingAmountDTO);
        return purchasingAmountDTO;
    }
}
