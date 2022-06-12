package com.example.entity;

import com.example.common.enumeration.ItemType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Item extends Identity {


    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_type")
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Column(name = "item_price")
    private BigDecimal itemPrice;

    @ManyToOne
    @JoinColumn
    private PurchaseOrder purchaseOrder;

}
