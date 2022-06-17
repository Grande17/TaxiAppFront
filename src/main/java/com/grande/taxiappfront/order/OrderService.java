package com.grande.taxiappfront.order;

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
public class OrderService {

    private RestTemplate restTemplate = new RestTemplate();

    public void createOrder(CreateOrder createOrder){
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/order")
                .queryParam("pickUpPlace",createOrder.getPickUpPlace())
                .queryParam("dropPlace",createOrder.getDropPlace())
                .queryParam("customer", createOrder.getCustomer())
                .build().encode().toUri();
        restTemplate.postForObject(url, createOrder, CreateOrder.class);
    }

    public List<Order> returnCustomerHistory(String id){
        if (id == null || id.isEmpty()){
            try {
                Order[] orders = restTemplate.getForObject("http://localhost:8080/v1/order", Order[].class);
                return Arrays.asList(ofNullable(orders).orElse(new Order[0]));
            }catch (RestClientException e){
                return new ArrayList<>();
            }
        }else {
            try {
                Order[] byCustomer = restTemplate.getForObject("http://localhost:8080/v1/order/history/" + id, Order[].class);
                return Arrays.asList(ofNullable(byCustomer).orElse(new Order[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        }
    }
    public List<Order> returnDriverHistory(String id) {
        if (id == null || id.isEmpty()) {
            try {
                Order[] response = restTemplate.getForObject("http://localhost:8080/v1/order", Order[].class);
                return Arrays.asList(ofNullable(response).orElse(new Order[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        } else {
            try {
                Order[] response = restTemplate.getForObject("http://localhost:8080/v1/order/driverHistory/" + id, Order[].class);
                return Arrays.asList(ofNullable(response).orElse(new Order[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        }
    }
    public List<Order> findByStatus(String status){
        if (status == null || status.isEmpty()){
            try {
                Order[] response = restTemplate.getForObject("http://localhost:8080/v1/order", Order[].class);
                return Arrays.asList(ofNullable(response).orElse(new Order[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        }else{
            try {
                Order[] response = restTemplate.getForObject("http://localhost:8080/v1/order/status/"+status, Order[].class);
                return Arrays.asList(ofNullable(response).orElse(new Order[0]));
            } catch (RestClientException e) {
                return new ArrayList<>();
            }
        }
    }

    public void updateStatus(String id, String status){
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/order/"+id+"/"+status)
                .build().encode().toUri();
        restTemplate.put(url,null);
    }
    public  void cancelOrder(String id){
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/order/cancel/"+id)
                .build().encode().toUri();
        restTemplate.put(url,null);
    }

}
