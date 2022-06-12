package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "PURCHASE_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "items",callSuper = true)
public class PurchaseOrder extends Identity {



    @Column(name = "order_name")
    private String orderName;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @OneToMany(mappedBy = "purchaseOrder")
    private Set<Item> items;
}
