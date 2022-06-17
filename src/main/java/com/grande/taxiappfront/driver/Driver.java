package com.grande.taxiappfront.driver;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grande.taxiappfront.car.AddCar;
import com.grande.taxiappfront.car.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
public class Driver {

    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String status;
    private Car car;
}
