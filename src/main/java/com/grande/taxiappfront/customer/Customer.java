package com.grande.taxiappfront.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Customer {

    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String phoneNumber;
    private String email;

}
