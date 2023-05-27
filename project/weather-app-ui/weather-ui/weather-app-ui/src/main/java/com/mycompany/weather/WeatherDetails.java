package com.mycompany.weather;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("WeatherDetails/:longitude/:latitude")
    private String longitude;
    private String latitude;
    public WeatherDetails(){
        H1 headerText = new H1("Weather Details");
        HorizontalLayout header = new HorizontalLayout(headerText);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        header.setDefaultVerticalComponentAlignment(Alignment.AUTO);
        add(header);
    }

}
