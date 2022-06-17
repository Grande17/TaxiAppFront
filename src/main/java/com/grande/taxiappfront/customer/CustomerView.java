package com.grande.taxiappfront.customer;

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

@Route("customer")
public class CustomerView extends VerticalLayout {
    Grid<Customer> grid = new Grid<>(Customer.class);
    TextField filterText = new TextField();
    TextField userToDelete = new TextField();
    Button goToMainMenu = new Button("Main menu");
    TextField name = new TextField();
    Button updateEmail = new Button("Update email");
    CustomerService customerService;
    CustomerForm customerForm;



    public CustomerView(CustomerService service){
        this.customerService = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureCustomerForm();

        add(
                getToolbar(),
                getContent()
        );
        updateList();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, customerForm);
        content.setFlexGrow(15,grid);
        content.setFlexGrow(1,customerForm);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureCustomerForm() {
        customerForm = new CustomerForm();
        customerForm.setWidth("25em");
    }

    private void updateList() {
        grid.setItems(customerService.getCustomers(filterText.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by username...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e-> updateList());
        name.setPlaceholder("Filter by name...");
        name.setClearButtonVisible(true);
        name.setValueChangeMode(ValueChangeMode.LAZY);
        name.addValueChangeListener(e->grid.setItems(customerService.findByName(name.getValue())));

        userToDelete.setPlaceholder("User to delete - ID");
        Button delete = new Button("Delete");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        delete.addClickListener(event -> {
            customerService.deleteCustomer(userToDelete.getValue());
            UI.getCurrent().getPage().reload();
        });

        Button updateView = new Button("Update");
        updateView.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        updateView.addClickListener(e-> UI.getCurrent().navigate("updateCustomer"));

        goToMainMenu.addClickListener(event -> UI.getCurrent().navigate(""));
        updateEmail.addClickListener(event -> UI.getCurrent().navigate("email"));

        HorizontalLayout toolbar = new HorizontalLayout(filterText,name,userToDelete, delete, updateView,updateEmail, goToMainMenu);
        toolbar.addClassName("toolbar");
        return toolbar;
    }


    private void configureGrid() {
        grid.addClassName("customer-grid");
        setSizeFull();
        grid.setColumns("id","name","surname","username");
        grid.getColumns().forEach(col->col.setAutoWidth(true));
        grid.addColumn(customer -> customer.getEmail()).setHeader("Email");
        grid.addColumn(customer -> customer.getPhoneNumber()).setHeader("Phone Number");


    }

}
