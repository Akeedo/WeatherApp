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
        grid.addColumn(City::getId).setHeader("ID");
        grid.addColumn(City::getName).setHeader("Name");
        grid.addColumn(City::getLongitude).setHeader("Longitude");
        grid.addColumn(City::getLatitude).setHeader("Latitude");
        grid.addColumn(City::getElevation).setHeader("Elevation");
        grid.addColumn(City::getFeatureCode).setHeader("Feature Code");
        grid.addColumn(City::getCountryCode).setHeader("Country Code");
        grid.addColumn(City::getAdmin1Id).setHeader("Admin1 ID");
        grid.addColumn(City::getAdmin2Id).setHeader("Admin2 ID");
        grid.addColumn(City::getTimezone).setHeader("Timezone");
        grid.addColumn(City::getPopulation).setHeader("Population");
        grid.addColumn(City::getCountryId).setHeader("Country ID");
        grid.addColumn(City::getCountry).setHeader("Country");
        grid.addColumn(City::getAdmin1).setHeader("Admin1");
        grid.addColumn(City::getAdmin2).setHeader("Admin2");
        grid.setColumns("id", "name", "longitude", "latitude", "elevation", "featureCode",
                "countryCode", "admin1Id", "admin2Id", "timezone", "population",
                "countryId", "country", "admin1", "admin2");
        grid.setItems();

        grid.addItemClickListener(event -> {
            City cityData = event.getItem();
            Notification.show("Clicked on " + cityData.getName());
            UI.getCurrent().navigate("WeatherDetails/" + cityData.getLongitude() + "/" + cityData.getLatitude());
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
