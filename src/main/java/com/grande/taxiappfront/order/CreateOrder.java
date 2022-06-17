package com.grande.taxiappfront.order;

import com.grande.taxiappfront.customer.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateOrder {

    private String pickUpPlace;
    private String dropPlace;
    private Customer customer;

}
