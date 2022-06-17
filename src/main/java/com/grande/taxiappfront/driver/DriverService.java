package com.grande.taxiappfront.driver;


import com.grande.taxiappfront.customer.Customer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Getter
@Service
@Slf4j
public class DriverService {
    private RestTemplate restTemplate = new RestTemplate();
    public void saveDriver(AddDriver driver) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/drivers")
                .queryParam("name",driver.getName())
                .queryParam("surname",driver.getSurname())
                .queryParam("phoneNumber",driver.getPhoneNumber())
                .queryParam("email",driver.getEmail())
                .queryParam("car",driver.getCar())
                .build().encode().toUri();
        restTemplate.postForObject(url,driver, AddDriver.class);
    }

    public List<Driver> getDrivers(String contains){
        if (contains == null || contains.isEmpty()) {
            try {
                Driver[] response = restTemplate.getForObject("http://localhost:8080/v1/drivers", Driver[].class);
                return Arrays.asList(ofNullable(response).orElse(new Driver[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        }else {
            try{
                Driver[] response2 = restTemplate.getForObject("http://localhost:8080/v1/drivers/name/"+contains, Driver[].class);
                return Arrays.asList(ofNullable(response2).orElse(new Driver[0]));
            }catch (RestClientException e){
                return new ArrayList<>();
            }
        }
    }
    public List<Driver> getDriverByEmail(String email){
        if (email == null || email.isEmpty()){
            try {
                Driver[] response = restTemplate.getForObject("http://localhost:8080/v1/drivers", Driver[].class);
                return Arrays.asList(ofNullable(response).orElse(new Driver[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        }else {
            try {
                Driver[] response = restTemplate.getForObject("http://localhost:8080/v1/drivers/email/" + email, Driver[].class);
                return Arrays.asList(ofNullable(response).orElse(new Driver[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        }
    }

    public void deleteDriver(String id){
        try {
            URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/drivers/")
                    .path(id).build().encode().toUri();
            restTemplate.delete(url);
        }catch (RestClientException e){
            log.error("Driver with given id doesn't exist");
        }
    }

    public Driver getById(String id){
        try {
            Driver driver = restTemplate.getForObject("http://localhost:8080/v1/drivers/"+id,Driver.class);
            return driver;
        }catch (RestClientException e){
            return null;
        }
    }
    public void updateDriver(Driver driver){
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/drivers")
                .build().encode().toUri();
        restTemplate.put(url,driver);
    }
    public void updateStatus(String id, String status){
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/drivers/status/"+id+"/"+status)
                .build().encode().toUri();
        restTemplate.put(url,null);
    }

}
