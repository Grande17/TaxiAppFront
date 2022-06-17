package com.grande.taxiappfront.car;

import com.grande.taxiappfront.driver.Driver;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class CarService {

    RestTemplate restTemplate = new RestTemplate();

    public Car getById(String id){
        try {
            Car car = restTemplate.getForObject("http://localhost:8080/v1/cars/"+id,Car.class);
            return car;
        }catch (RestClientException e){
            return null;
        }
    }

    public List<Car> getAll(String plates) {
        if (plates == null || plates.isEmpty()) {
            try {
                Car[] cars = restTemplate.getForObject("http://localhost:8080/v1/cars", Car[].class);
                return Arrays.asList(ofNullable(cars).orElse(new Car[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        }else{
            try{
                Car[] cars = restTemplate.getForObject("http://localhost:8080/v1/cars/plates/"+plates, Car[].class);
                return Arrays.asList(ofNullable(cars).orElse(new Car[0]));
            }catch (RestClientException e) {
                return new ArrayList<>();
            }
        }
    }

    public void updateCar(Car car){
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/cars")
                .build().encode().toUri();
        restTemplate.put(url,car);
    }
}
