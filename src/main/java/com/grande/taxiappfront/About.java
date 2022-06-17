package com.grande.taxiappfront;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.net.URI;

@Route("about")
public class About extends VerticalLayout {

    public About(){


        add(new H2("Taxi App"));
        add(new Paragraph("Front-end application created with the Vaadin library for the Kodilla final exam."));
        add(new Paragraph("Created and developed by Mateusz Markowski aka Grande"));
        add(new Anchor("https://github.com/Grande17/AppTaxi","Back-end app - Github!"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
