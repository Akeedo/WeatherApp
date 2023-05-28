package com.mycompany.weather;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route("WeatherDetails")
public class WeatherDetails extends VerticalLayout implements HasUrlParameter<String>, BeforeEnterObserver {
    private String longitude;
    private String latitude;
    private HorizontalLayout layout;

    private Grid<DailyWeather> gridDailyWeather = new Grid<>(DailyWeather.class);

    private static final Logger LOGGER = LogManager.getLogger(WeatherDetails.class);


    public WeatherDetails(){
        H1 headerText = new H1("Weather Details");
        HorizontalLayout header = new HorizontalLayout(headerText);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        header.setDefaultVerticalComponentAlignment(Alignment.AUTO);
        add(header);

        layout = new HorizontalLayout();
        add(layout);
    }


    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String parameter){
        List<String> splitParameters = Arrays.asList(parameter.split("/"));
        if(splitParameters.size() == 2){
            longitude = splitParameters.get(0);
            latitude = splitParameters.get(1);
            layout.removeAll();

            Label longitudeLabel = new Label("Longitude: " + longitude);
            Label latitudeLabel = new Label("Latitude: " + latitude);
            layout.add(longitudeLabel, latitudeLabel);
        }
    }


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        System.out.println("Hello World");
    }

    private void fetchDailyWeather(String longitude, String latitude) throws IOException, InterruptedException {
        double longitudeAsDouble = Double.parseDouble(longitude);
        double latitudeAsDouble = Double.parseDouble(latitude);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.open-meteo.com/v1/forecast?latitude="+latitudeAsDouble+"&longitude="+longitudeAsDouble+"4&daily=temperature_2m_max,temperature_2m_min,apparent_temperature_max,rain_sum,windspeed_10m_max&current_weather=true&timezone=Europe%2FLondon"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String responseBody = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            try{
                WeatherDataDaily dailyData = objectMapper.readValue(responseBody, WeatherDataDaily.class);
                List<String> timeList = dailyData.getDaily().getTime();

                List<Double> maxTemp2mList = dailyData.getDaily().getTemperature2mMax();
                List<Double> minTemp2mList = dailyData.getDaily().getTemperature2mMin();
                List<Double> maxApparentTempList = dailyData.getDaily().getApparentTemperatureMax();
                List<Double> rainSumList = dailyData.getDaily().getRainSum();
                List<Double> maxWindSpeed10mList = dailyData.getDaily().getWindSpeed10mMax();
            }catch (JsonProcessingException e){
                e.printStackTrace();
            }
        } else {
            String errorMessage = "Failed to fetch location data. HTTP Status Code: " + response.statusCode();
            Notification.show(errorMessage);
        }
    }
}
