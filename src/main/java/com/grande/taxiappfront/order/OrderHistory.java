package com.grande.taxiappfront.order;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

@Route("orderHistory")
public class OrderHistory extends VerticalLayout {

    OrderService orderService = new OrderService();
    TextField customerId = new TextField();
    TextField driverId = new TextField();
    ComboBox<String> filterByStatus = new ComboBox<>();
    Button changeStatus = new Button("Change status");
    Button cancelOrder = new Button("Cancel order");
    Button mainMenu = new Button("Main menu");

    private Grid<Order> grid = new Grid<>(Order.class);



    public OrderHistory(){


        add(toolbar(),getContent());

        configureGrid();
        updateList();

    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }
    private void configureGrid() {
        setSizeFull();
        grid.setColumns("id","pickUpPlace","dropPlace","estimatedCost","estimatedDuration","status");
        grid.getColumns().forEach(col->col.setAutoWidth(true));
        grid.addColumn(order -> order.getCustomer().getId()).setHeader("Customer");
        grid.addColumn(order -> order.getDriver().getId()).setHeader("Driver");
    }
    private void updateList() {
        grid.setItems(orderService.returnCustomerHistory(customerId.getValue()));
    }
    private Component toolbar(){
        customerId.setPlaceholder("Filter by customer ID");
        customerId.setClearButtonVisible(true);
        customerId.setValueChangeMode(ValueChangeMode.LAZY);
        customerId.addValueChangeListener(e-> updateList());

        driverId.setPlaceholder("Filter by river ID");
        driverId.setClearButtonVisible(true);
        driverId.setValueChangeMode(ValueChangeMode.LAZY);
        driverId.addValueChangeListener(e-> grid.setItems(orderService.returnDriverHistory(driverId.getValue())));

        changeStatus.addClickListener(event -> UI.getCurrent().navigate("changeStatus"));

        cancelOrder.addClickListener(event -> UI.getCurrent().navigate("cancelOrder"));

        mainMenu.addClickListener(event -> UI.getCurrent().navigate(""));

        HorizontalLayout horizontalLayout = new HorizontalLayout(customerId,driverId,comboBoxSearch(),changeStatus,cancelOrder,mainMenu);
        return horizontalLayout;
    }
    private Component comboBoxSearch(){
        filterByStatus.setItems("ACTIVE","IN_PROGRESS","CANCELLED","FINISHED");
        filterByStatus.setPlaceholder("Filter by status");
        filterByStatus.addValueChangeListener(e->grid.setItems(orderService.findByStatus(filterByStatus.getValue())));
        return filterByStatus;
    }
}
