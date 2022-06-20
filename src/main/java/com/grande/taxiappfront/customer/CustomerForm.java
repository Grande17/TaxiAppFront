package com.grande.taxiappfront.customer;

import com.grande.taxiappfront.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class CustomerForm extends FormLayout {
    MainView mainView = new MainView();
    TextField name = new TextField("Name");
    TextField surname = new TextField("Surname");
    TextField username = new TextField("Username");
    EmailField email = new EmailField("Email");
    TextField phoneNumber = new TextField("Phone");
    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    CustomerService service = new CustomerService();


    public CustomerForm() {
        addClassName("customer-form");
        validator();

        add(new H2("Register Customer!"));
        add(name,surname,username,email,phoneNumber,createButtonLayout());
        saveCustomer();
        cancelListener();


    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new VerticalLayout(save,cancel);}

    private void saveCustomer(){
        save.addClickListener(event ->{
            AddCustomerDto newCustomer = new AddCustomerDto();
            newCustomer.setName(name.getValue());
            newCustomer.setSurname(surname.getValue());
            newCustomer.setUsername(username.getValue());
            newCustomer.setPhoneNumber(phoneNumber.getValue());
            newCustomer.setEmail(email.getValue());
            service.createCustomer(newCustomer);
            UI.getCurrent().getPage().reload();
        });
    }
    private void cancelListener(){
        cancel.addClickListener(event -> {
            name.clear();
            surname.clear();
            username.clear();
            email.clear();
            phoneNumber.clear();
        });
    }
    private void validator(){
        name.setRequired(true);
        surname.setRequired(true);
        username.setRequired(true);
        phoneNumber.setRequired(true);
        name.setMinLength(2);
        surname.setMinLength(2);
        username.setMinLength(2);
        phoneNumber.setMinLength(2);

    }
}
