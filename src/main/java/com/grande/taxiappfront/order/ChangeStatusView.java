package com.grande.taxiappfront.order;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("changeStatus")
public class ChangeStatusView extends VerticalLayout {

    ComboBox<String> comboBox = new ComboBox<>();
    TextField orderId = new TextField("Order ID");
    Button confirm = new Button("Confirm");
    Button goBack = new Button("Go back");

    @Autowired
    OrderService orderService;

    public ChangeStatusView(){

        add(new H2("Change order status!"));
        add(comboBox(),orderId,confirm,goBack);
        buttonsConfig();

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    }
    private ComboBox comboBox(){
        comboBox.setItems("ACTIVE","IN_PROGRESS","FINISHED");
        comboBox.setPlaceholder("Choose status");
        return comboBox;
    }
    private void buttonsConfig(){
        confirm.addClickListener(event -> {
            orderService.updateStatus(orderId.getValue(),comboBox.getValue());
            UI.getCurrent().navigate("orderHistory");
        });
        goBack.addClickListener(event -> UI.getCurrent().navigate("orderHistory"));

    }
}
