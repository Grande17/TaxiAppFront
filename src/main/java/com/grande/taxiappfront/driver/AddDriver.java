package com.grande.taxiappfront.driver;

import com.grande.taxiappfront.car.AddCar;
import com.grande.taxiappfront.car.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddDriver {

    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private AddCar car;
}
