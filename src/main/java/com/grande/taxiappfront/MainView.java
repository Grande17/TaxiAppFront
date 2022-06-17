package com.grande.taxiappfront;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;



@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        setSpacing(false);

        add(buttonMenu());
        add(new H2("Taxi App"));
        add(new Paragraph("Simple way to get a cab 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    private HorizontalLayout buttonMenu(){
        Button customer = new Button("Customer",e-> UI.getCurrent().navigate("customer"));
        Button driver = new Button("Driver",e-> UI.getCurrent().navigate("driver"));
        Button car = new Button("Car",e-> UI.getCurrent().navigate("car"));
        Button order = new Button("Order",e-> UI.getCurrent().navigate("orderHistory"));
        Button createOrder= new Button("Order your cab",e-> UI.getCurrent().navigate("order"));
        Button about = new Button("About",e-> UI.getCurrent().navigate("about"));

        HorizontalLayout menu = new HorizontalLayout(customer,driver,car,order,createOrder,about);

        return menu;
    }

}