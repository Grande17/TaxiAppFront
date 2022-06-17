package com.grande.taxiappfront.customer;

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
public class CustomerService {
    private RestTemplate restTemplate = new RestTemplate();

    public List<Customer> getCustomers(String contains){
        if (contains == null || contains.isEmpty()) {
            try {
                Customer[] response = restTemplate.getForObject("http://localhost:8080/v1/customers", Customer[].class);
                return Arrays.asList(ofNullable(response).orElse(new Customer[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        }else {
            try{
                Customer[] response2 = restTemplate.getForObject("http://localhost:8080/v1/customers/username/"+contains, Customer[].class);
                return Arrays.asList(ofNullable(response2).orElse(new Customer[0]));
            }catch (RestClientException e){
                return new ArrayList<>();
            }
        }
    }
    public void createCustomer(final AddCustomerDto addCustomerDto){
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/customers")
                .queryParam("name",addCustomerDto.getName())
                .queryParam("surname",addCustomerDto.getSurname())
                .queryParam("username",addCustomerDto.getUsername())
                .queryParam("phoneNumber",addCustomerDto.getPhoneNumber())
                .queryParam("email",addCustomerDto.getEmail())
                .build().encode().toUri();
        restTemplate.postForObject(url,addCustomerDto,AddCustomerDto.class);
    }
    public void deleteCustomer(String id){
        try {
            URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/customers/")
                    .path(id).build().encode().toUri();
            restTemplate.delete(url);
        }catch (RestClientException e){
            log.error("Customer with given id doesn't exist");
        }
    }
    public List<Customer> findByName(String name){
        if (name == null || name.isEmpty()){
            try {
                Customer[] response = restTemplate.getForObject("http://localhost:8080/v1/customers", Customer[].class);
                return Arrays.asList(ofNullable(response).orElse(new Customer[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        }else {

            try {
                Customer[] respone = restTemplate.getForObject("http://localhost:8080/v1/customers/name/" + name, Customer[].class);
                return Arrays.asList(ofNullable(respone).orElse(new Customer[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        }
    }
    public void updateCustomer(Customer customer){
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/customers")
                    .build().encode().toUri();
        restTemplate.put(url,customer);
    }
    public Customer getById(Integer id){
        Customer customer = restTemplate.getForObject("http://localhost:8080/v1/customers/"+id, Customer.class);
        return customer;
    }
}
