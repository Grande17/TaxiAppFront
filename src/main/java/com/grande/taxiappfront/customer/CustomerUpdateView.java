package com.grande.taxiappfront.customer;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("updateCustomer")
public class CustomerUpdateView extends VerticalLayout {

    @Autowired
    CustomerService service;

    TextField id = new TextField("Id");
    TextField name = new TextField("Name");
    TextField surname = new TextField("Surname");
    TextField username = new TextField("Username");
    EmailField email = new EmailField("Email");
    TextField phoneNumber = new TextField("PhoneNumber");
    Button save = new Button("Save");
    Button discard = new Button("Discard");
    Button back = new Button("Go back");

    public CustomerUpdateView() {
        add(new H2("Update customer information"));
        add(id,name,surname,username,email,phoneNumber,save,discard,back);
        updateCustomer();
        discardAndBack();
        setSizeFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        validator();
    }
    private void updateCustomer() {
        save.addClickListener(event -> {
            Customer customer = new Customer();
            customer.setId(Integer.valueOf(id.getValue()));
            customer.setName(name.getValue());
            customer.setSurname(surname.getValue());
            customer.setUsername(username.getValue());
            customer.setPhoneNumber(phoneNumber.getValue());
            customer.setEmail(email.getValue());
            service.updateCustomer(customer);
            UI.getCurrent().navigate("customer");
        });
    }
    private void discardAndBack(){
        discard.addClickListener(e->{
            id.clear();
            name.clear();
            surname.clear();
            username.clear();
            phoneNumber.clear();
            email.clear();
        });
        back.addClickListener(event -> UI.getCurrent().navigate("customer"));
    }
    private void validator(){
        id.setRequired(true);
        name.setRequired(true);
        surname.setRequired(true);
        username.setRequired(true);
        phoneNumber.setRequired(true);
        id.setMinLength(1);
        name.setMinLength(2);
        surname.setMinLength(2);
        username.setMinLength(2);
        phoneNumber.setMinLength(2);

    }

}
