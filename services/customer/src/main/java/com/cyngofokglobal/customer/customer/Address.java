package com.cyngofokglobal.customer.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
@Getter
@Setter
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;
}
