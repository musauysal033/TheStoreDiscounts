package com.example.entity;


import com.example.common.enumeration.CustomerType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "purchaseOrders",callSuper = true)
public class Customer extends Identity{


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "customer_type")
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<PurchaseOrder> purchaseOrders;


}
