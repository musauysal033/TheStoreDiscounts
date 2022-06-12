package com.example.common.dto;

import com.example.common.enumeration.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime createdDate;
    private CustomerType customerType;
}
