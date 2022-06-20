package com.grande.taxiappfront.customer;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("email")
public class UpdateEmailView extends VerticalLayout {

    EmailField email = new EmailField("New email");
    Button confirm = new Button("Confirm");
    TextField id = new TextField("User ID");
    Button goBack = new Button("Go back");

    @Autowired
    CustomerService service;

    public UpdateEmailView(){

        add(email,id,confirm,goBack);
        configuration();

        setSizeFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        validator();
    }

    private void configuration(){
        confirm.addClickListener(event -> {
            service.updateEmail(id.getValue(),email.getValue());
            UI.getCurrent().navigate("customer");
        });
        goBack.addClickListener(event -> UI.getCurrent().navigate("customer"));
    }
    private void validator(){
        id.setRequired(true);
        id.setMinLength(1);

    }

}
