package com.grande.taxiappfront.driver;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import static com.grande.taxiappfront.driver.DriverStatus.*;


@Route("updateDriverStatus")
public class UpdateDriverStatusView extends VerticalLayout {

    TextField id = new TextField("Driver ID");
    ComboBox<String> status = new ComboBox<>("Choose status");
    Button save = new Button("Save");
    Button goBack = new Button("Go back");
    @Autowired
    DriverService driverService;

    public UpdateDriverStatusView() {
        add(new H2("Update driver status!"));
        add(id,status,save,goBack);
        saveAndGoBackButton();
        statusConfig();
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
    private void saveAndGoBackButton(){
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        save.addClickListener(event -> {
            driverService.updateStatus(id.getValue(), status.getValue());
            UI.getCurrent().navigate("driver");
        });
        goBack.addClickListener(event -> UI.getCurrent().navigate("driver"));
    }
    private void statusConfig(){
        status.setItems(ACTIVE.name,INACTIVE.name,BREAK.name,BUSY.name);
    }
}
