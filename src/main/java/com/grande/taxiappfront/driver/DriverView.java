package com.grande.taxiappfront.driver;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("driver")
public class DriverView extends VerticalLayout {
    Grid<Driver> grid = new Grid<>(Driver.class);
    TextField filterText = new TextField();
    TextField driverToDelete = new TextField();
    Button goToMainMenu = new Button("Main menu");
    TextField email = new TextField();
    DriverService driverService;
    DriverForm driverForm;



    public DriverView(DriverService driverService){
        this.driverService = driverService;

        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureCustomerForm();


        add(getToolbar(), getContent());
        updateList();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, driverForm);
        content.setFlexGrow(15,grid);
        content.setFlexGrow(1,driverForm);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureCustomerForm() {
        driverForm = new DriverForm();
        driverForm.setWidth("25em");
    }

    private void updateList() {
        grid.setItems(driverService.getDrivers(filterText.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by surname...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e-> {
            updateList();
        });
        email.setPlaceholder("Filter by email...");
        email.setClearButtonVisible(true);
        email.setValueChangeMode(ValueChangeMode.EAGER);
        email.addValueChangeListener(e->grid.setItems(driverService.getDriverByEmail(email.getValue())));

        driverToDelete.setPlaceholder("Driver to delete - ID");
        Button delete = new Button("Delete");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        delete.addClickListener(event -> {
            driverService.deleteDriver(driverToDelete.getValue());
            UI.getCurrent().getPage().reload();
        });

        Button updateView = new Button("Update");
        updateView.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        updateView.addClickListener(e-> UI.getCurrent().navigate("updateDriver"));
        Button updateStatus = new Button("Update status");
        updateStatus.addClickListener(event -> UI.getCurrent().navigate("updateDriverStatus"));

        goToMainMenu.addClickListener(event -> UI.getCurrent().navigate(""));

        HorizontalLayout toolbar = new HorizontalLayout(filterText,email,driverToDelete,delete, updateView,updateStatus, goToMainMenu);
        toolbar.addClassName("toolbar");
        return toolbar;
    }


    private void configureGrid() {
        grid.addClassName("customer-grid");
        setSizeFull();
        grid.setColumns("id","name","surname","phoneNumber","email","status","car");
        grid.getColumns().forEach(col->col.setAutoWidth(true));

    }

}
