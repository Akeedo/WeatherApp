package com.mycompany.weather;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
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
import java.util.Optional;

@Route("WeatherDetails")
public class WeatherDetails extends VerticalLayout implements HasUrlParameter<String>, BeforeEnterObserver {
    private String longitude;
    private String latitude;
    private HorizontalLayout layout;

    private Grid<DailyWeather> gridDailyWeather = new Grid<>();

    private static final Logger LOGGER = LogManager.getLogger(WeatherDetails.class);


    public WeatherDetails(){
        H1 headerText = new H1("Weather Details");
        HorizontalLayout header = new HorizontalLayout(headerText);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        header.setDefaultVerticalComponentAlignment(Alignment.AUTO);
        add(header);

        layout = new HorizontalLayout();


        gridDailyWeather.addColumn(DailyWeather::getTime).setHeader("Time");
        gridDailyWeather.addColumn(DailyWeather::getTemperature2mMax).setHeader("Max Temperature"); // make sure getTemperature2mMax exists in DailyWeather
        gridDailyWeather.addColumn(DailyWeather::getTemperature2mMin).setHeader("Min Temperature"); // make sure getTemperature2mMin exists in DailyWeather
        gridDailyWeather.addColumn(DailyWeather::getApparentTemperatureMax).setHeader("Apparent Max Temperature"); // make sure getApparentTemperatureMax exists in DailyWeather
        gridDailyWeather.addColumn(DailyWeather::getRainSum).setHeader("Rain Sum"); // make sure getRainSum exists in DailyWeather
        gridDailyWeather.addColumn(DailyWeather::getWindSpeed10mMax).setHeader("Max Wind Speed"); // make sure getWindSpeed10mMax exists in DailyWeather

        add(layout,gridDailyWeather);
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

            try {
                fetchDailyWeather(longitude,latitude);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        try {
            DailyWeatherForecast weatherForecast = fetchDailyWeather(longitude, latitude);
            Optional<List<DailyWeather>> dailyWeather = convertDailyWeatherToList(weatherForecast);
            setColumnsToGridDailyWeatherGrid(dailyWeather);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void setColumnsToGridDailyWeatherGrid(Optional<List<DailyWeather>> dailyWeatherOpt){
        if(dailyWeatherOpt.isPresent()){
            List<DailyWeather> dailyWeathers = dailyWeatherOpt.get();
            gridDailyWeather.removeAllColumns();

            gridDailyWeather.addColumn(DailyWeather::getTime)
                    .setHeader("Time")
                    .setSortable(true);

            gridDailyWeather.addColumn(DailyWeather::getTemperature2mMax)
                    .setHeader("Max Temp (2m)")
                    .setSortable(true);

            gridDailyWeather.addColumn(DailyWeather::getTemperature2mMin)
                    .setHeader("Min Temp (2m)")
                    .setSortable(true);

            gridDailyWeather.addColumn(DailyWeather::getApparentTemperatureMax)
                    .setHeader("Max Apparent Temp")
                    .setSortable(true);

            gridDailyWeather.addColumn(DailyWeather::getRainSum)
                    .setHeader("Total Rainfall")
                    .setSortable(true);

            gridDailyWeather.addColumn(DailyWeather::getWindSpeed10mMax)
                    .setHeader("Max Wind Speed (10m)")
                    .setSortable(true);

            gridDailyWeather.setItems(dailyWeathers);
        } else{
            Notification.show("No data available", 3000, Notification.Position.MIDDLE);
        }
    }
    private DailyWeatherForecast fetchDailyWeather(String longitude, String latitude) throws IOException, InterruptedException {
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
                DailyWeatherResponse dailyWeatherResponse = objectMapper.readValue(responseBody, DailyWeatherResponse.class);

                DailyWeatherForecast weatherForecast = dailyWeatherResponse.getDailyWeatherForecast();
                return weatherForecast;
               
            }catch (JsonProcessingException e){
                e.printStackTrace();
                return null;
            }
        } else {
            String errorMessage = "Failed to fetch location data. HTTP Status Code: " + response.statusCode();
            Notification.show(errorMessage);
            return null;
        }
    }



    public Optional<List<DailyWeather>> convertDailyWeatherToList(DailyWeatherForecast dailyWeatherForecast) {
        if (dailyWeatherForecast == null || dailyWeatherForecast.getTime() == null || dailyWeatherForecast.getTime().isEmpty()) {
            return Optional.empty();
        }

        List<DailyWeather> weatherRowList = new ArrayList<>();
        for (int i = 0; i < dailyWeatherForecast.getTime().size(); i++) {
            DailyWeather row = new DailyWeather(
                    dailyWeatherForecast.getTime().get(i),
                    dailyWeatherForecast.getTemperature2mMax().get(i),
                    dailyWeatherForecast.getTemperature2mMin().get(i),
                    dailyWeatherForecast.getApparentTemperatureMax().get(i),
                    dailyWeatherForecast.getRainSum().get(i),
                    dailyWeatherForecast.getWindSpeed10mMax().get(i)
            );
            weatherRowList.add(row);
        }
        return Optional.of(weatherRowList);
    }

}
