package com.grande.taxiappfront.order;

import com.grande.taxiappfront.customer.Customer;
import com.grande.taxiappfront.driver.Driver;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class Order {

    private Integer id;
    private String pickUpPlace;
    private String dropPlace;
    private Customer customer;
    private BigDecimal estimatedCost;
    private LocalTime estimatedDuration;
    private String status;
    private Driver driver;
}
