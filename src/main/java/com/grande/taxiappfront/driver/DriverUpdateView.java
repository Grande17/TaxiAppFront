package com.grande.taxiappfront.driver;

import com.grande.taxiappfront.car.CarService;
import com.grande.taxiappfront.customer.Customer;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("updateDriver")
public class DriverUpdateView extends VerticalLayout {


    DriverService service = new DriverService();
    TextField id = new TextField("Id");
    TextField name = new TextField("Name");
    TextField surname = new TextField("Surname");
    TextField phone = new TextField("Phone Number");
    EmailField email = new EmailField("Email");
    Button save = new Button("Save");
    Button discard = new Button("Discard");
    Button back = new Button("Go back");

    public DriverUpdateView() {
        add(new H2("Update driver!"));
        add(id,name,surname,phone,email,save,discard,back);
        updateCustomer();
        discardAndBack();
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
    private void updateCustomer() {
        save.addClickListener(event -> {
            Driver driver = new Driver();
            driver.setId(Integer.valueOf(id.getValue()));
            driver.setName(name.getValue());
            driver.setSurname(surname.getValue());
            driver.setPhoneNumber(phone.getValue());
            driver.setEmail(email.getValue());
            driver.setCar(service.getById(id.getValue()).getCar());
            service.updateDriver(driver);
            UI.getCurrent().navigate("driver");
        });
    }
    private void discardAndBack(){
        discard.addClickListener(e->{
            id.clear();
            name.clear();
            surname.clear();
            email.clear();
            phone.clear();
        });
        back.addClickListener(event -> UI.getCurrent().navigate("driver"));
    }
}
