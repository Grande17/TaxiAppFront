package com.grande.taxiappfront.car;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("car")
public class CarView extends VerticalLayout {

    Grid<Car> grid = new Grid<>(Car.class);
    TextField filterText = new TextField();
    Button goToMainMenu = new Button("Main menu");
    CarService carService = new CarService();




    public CarView(){
        setSizeFull();

        configureGrid();

        add(getToolbar(), getContent());
        updateList();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void updateList() {
        grid.setItems(carService.getAll(filterText.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by plates number...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e-> updateList());




        Button updateView = new Button("Update");
        updateView.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        updateView.addClickListener(e-> UI.getCurrent().navigate("updateCar"));


        goToMainMenu.addClickListener(event -> UI.getCurrent().navigate(""));



        HorizontalLayout toolbar = new HorizontalLayout(filterText,updateView,goToMainMenu);
        toolbar.addClassName("toolbar");
        return toolbar;
    }


    private void configureGrid() {
        grid.addClassName("customer-grid");
        setSizeFull();
        grid.setColumns("id","carBrand","model","bodyType","licensePlateNumber");
        grid.getColumns().forEach(col->col.setAutoWidth(true));



    }
}
