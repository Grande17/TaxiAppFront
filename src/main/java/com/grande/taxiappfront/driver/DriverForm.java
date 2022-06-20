package com.grande.taxiappfront.driver;


import com.grande.taxiappfront.car.AddCar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;


public class DriverForm extends FormLayout {


    TextField name = new TextField("Name");
    TextField surname = new TextField("Surname");
    TextField phoneNumber = new TextField("Phone");
    EmailField email = new EmailField("Email");

    TextField carBrand = new TextField("Car brand");
    TextField model = new TextField("Car model");
    ComboBox<String> bodyType = new ComboBox<>("Body type");
    TextField plates = new TextField("Plates number");

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    DriverService service = new DriverService();


    public DriverForm() {
        addClassName("driver-form");


        add(new H2("Register new driver!"));
        add(name,surname,phoneNumber,email,carBrand,model,bodyType,plates,createButtonLayout());
        config();
        saveDriver();
        cancelListener();
        validator();




    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new VerticalLayout(save,cancel);}

    private void saveDriver(){
        save.addClickListener(event ->{
            AddDriver driver = new AddDriver();
            AddCar car = new AddCar();
            car.setCarBrand(carBrand.getValue());
            car.setModel(model.getValue());
            car.setBodyType(bodyType.getValue());
            car.setLicensePlateNumber(plates.getValue());
            driver.setName(name.getValue());
            driver.setSurname(surname.getValue());
            driver.setEmail(email.getValue());
            driver.setPhoneNumber(phoneNumber.getValue());
            driver.setCar(car);
            service.saveDriver(driver);

            UI.getCurrent().getPage().reload();
        });
    }
    private void cancelListener(){
        cancel.addClickListener(event -> {
            name.clear();
            surname.clear();
            email.clear();
            phoneNumber.clear();
            carBrand.clear();
            model.clear();
            bodyType.clear();
            plates.clear();
        });
    }
    private void config(){
        bodyType.setItems("CITY CAR","COUPE","CABRIO","KOMBI","SEDAN","SUV");
    }
    private void validator(){
        name.setRequired(true);
        surname.setRequired(true);
        phoneNumber.setRequired(true);
        carBrand.setRequired(true);
        model.setRequired(true);
        bodyType.setRequired(true);
        plates.setRequired(true);

        name.setMinLength(1);
        surname.setMinLength(1);
        phoneNumber.setMinLength(1);
        carBrand.setMinLength(1);
        model.setMinLength(1);
        plates.setMinLength(1);

    }
}
