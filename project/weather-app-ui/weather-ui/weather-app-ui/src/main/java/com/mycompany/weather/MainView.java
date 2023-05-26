package com.mycompany.weather;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {
    private Grid<CityData> grid = new Grid<>(CityData.class);
    private TextField filter = new TextField();
    private Button searchButton = new Button("Search");
    public MainView() {
        filter.setPlaceholder("Filter by City name...");
        grid.addColumn(CityData::getCity).setHeader("Name");
        grid.addColumn(CityData::getLongitude).setHeader("Longitude");
        grid.addColumn(CityData::getLatitude).setHeader("Latitude");
        grid.addColumn(CityData::getState).setHeader("State");
        grid.addColumn(CityData::getCountry).setHeader("Country");
        grid.setColumns("city", "longitude", "latitude", "state", "country");
        grid.setItems();

        grid.addItemClickListener(event -> {
            CityData cityData = event.getItem();
            Notification.show("Clicked on " + cityData.getCity());
        });

        searchButton.addClickListener(click -> {
            try {
                fetchLocations(filter.getValue());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                Notification.show("An error occurred while fetching location data. Please try again.");
            }
        });

        add(filter, searchButton, grid);
    }

    private void fetchLocations(String cityName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.openweathermap.org/geo/1.0/direct?q="+cityName+"&limit=5&appid=e6e1309d61b8430fe739ae6b30ff3dd2"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String responseBody = response.body();
            JSONArray json = new JSONArray(responseBody);
            grid.setItems(json.toList().stream().map(o -> new CityData(new JSONObject((Map) o))));
        } else {
            String errorMessage = "Failed to fetch location data. HTTP Status Code: " + response.statusCode();
            Notification.show(errorMessage);
        }
    }
}
