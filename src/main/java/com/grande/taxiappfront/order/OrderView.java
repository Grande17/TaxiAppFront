package com.grande.taxiappfront.order;

import com.grande.taxiappfront.customer.Customer;
import com.grande.taxiappfront.customer.CustomerService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("order")
public class OrderView extends VerticalLayout {

    private TextField pickUpPlace = new TextField("Pick Up Place");
    private TextField drop = new TextField("Destination Place");
    private TextField customerId = new TextField("Your ID");
    private Button confirm = new Button("Confirm");
    private Button cancel = new Button("Cancel");
    private Button back = new Button("Go back");

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;


    public OrderView() {
        setSizeFull();

        add(new H2("Order your taxi!"));
        buttonsConfig();
        add(pickUpPlace,drop,customerId,confirm,cancel,back);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    }

    private void buttonsConfig(){
        pickUpPlace.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        confirm.addClickListener(event -> {
            CreateOrder createOrder = new CreateOrder();
            createOrder.setPickUpPlace(pickUpPlace.getValue());
            createOrder.setDropPlace(drop.getValue());
            createOrder.setCustomer(customerService.getById(Integer.valueOf(customerId.getValue())));
            orderService.createOrder(createOrder);

        });

        cancel.addClickListener(event -> {
            pickUpPlace.clear();
            drop.clear();
            customerId.clear();
        });

        back.addClickListener(event -> UI.getCurrent().navigate(""));

    }

}
