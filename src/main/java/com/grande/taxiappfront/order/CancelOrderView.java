package com.grande.taxiappfront.order;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("cancelOrder")
public class CancelOrderView extends VerticalLayout {

    TextField cancel = new TextField("Order ID");
    Button confirm = new Button("Confirm");
    Button goBack = new Button("Go back");
    @Autowired
    OrderService orderService;

    public CancelOrderView(){

        add(new H2("Cancel order!"));
        add(cancel,confirm,goBack);
        config();

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
    public void config(){
        confirm.addClickListener(event -> {
            orderService.cancelOrder(cancel.getValue());
            UI.getCurrent().navigate("orderHistory");
        });
        goBack.addClickListener(event -> UI.getCurrent().navigate("orderHistory"));
    }
}
