package com.grande.taxiappfront.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddCustomerDto {

    private String name;
    private String surname;
    private String username;
    private String phoneNumber;
    private String email;
}
