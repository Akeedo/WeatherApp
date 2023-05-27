package com.mycompany.weather;


import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.Arrays;
import java.util.List;

@Route("WeatherDetails/:longitude/:latitude")
public class WeatherDetails extends VerticalLayout implements HasUrlParameter<String> {
    private String longitude;
    private String latitude;

    public WeatherDetails(){
        H1 headerText = new H1("Weather Details");
        HorizontalLayout header = new HorizontalLayout(headerText);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        header.setDefaultVerticalComponentAlignment(Alignment.AUTO);
        add(header);

        HorizontalLayout layout = new HorizontalLayout();
        Label label = new Label(longitude);
        Label label1 = new Label(latitude);
        layout.add(label);
        layout.add(label1);
        add(layout);

    }


    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String parameter){
        List<String> splitParameters = Arrays.asList(parameter.split("/"));
        if(splitParameters.size() == 2){
            longitude = splitParameters.get(0);
            latitude = splitParameters.get(1);
        }
    }

}
