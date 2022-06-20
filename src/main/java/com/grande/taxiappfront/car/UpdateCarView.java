package com.grande.taxiappfront.car;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("updateCar")
public class UpdateCarView extends VerticalLayout {

    TextField id = new TextField("Id");
    TextField carBrand = new TextField("Car Brand");
    TextField model = new TextField("Model");
    ComboBox<String> bodyType = new ComboBox<>("Body Type");
    TextField registration = new TextField("Plates number");
    Button save = new Button("Save");
    Button goBack = new Button("Go back");
    @Autowired
    CarService carService;

    public UpdateCarView(){
        setSizeFull();
        add(new H2("Update car!"));
        add(id,carBrand,model,bodyType,registration,save,goBack);
        buttonConfig();
        bodyTypeConfig();

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        validator();
    }
    private void buttonConfig(){
        save.addClickListener(event -> {
            Car car = new Car();
            car.setId(Integer.valueOf(id.getValue()));
            car.setCarBrand(carBrand.getValue());
            car.setModel(model.getValue());
            car.setBodyType(bodyType.getValue());
            car.setLicensePlateNumber(registration.getValue());
            carService.updateCar(car);
            UI.getCurrent().navigate("car");
        });
        goBack.addClickListener(event -> UI.getCurrent().navigate("car"));
    }
    private void bodyTypeConfig(){
        bodyType.setItems("CITY CAR","COUPE","CABRIO","KOMBI","SEDAN","SUV");
    }

    private void validator(){
        id.setRequired(true);
        carBrand.setRequired(true);
        model.setRequired(true);
        bodyType.setRequired(true);
        registration.setRequired(true);
        id.setMinLength(1);
        carBrand.setMinLength(1);
        model.setMinLength(1);
        registration.setMinLength(1);

    }
}
