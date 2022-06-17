package com.grande.taxiappfront.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private Integer id;
    private String carBrand;
    private String model;
    private String bodyType;
    private String licensePlateNumber;

    @Override
    public String toString() {
        return id.toString();
    }
}
