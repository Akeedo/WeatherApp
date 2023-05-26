package com.mycompany.weather;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {
    private Grid<JSONObject> grid = new Grid<>(JSONObject.class);
    private TextField filter = new TextField();
    private Button searchButton = new Button("Search");

    public MainView() {
        filter.setPlaceholder("Filter by location name...");
        grid.setColumns("location");
        grid.setItems();
        grid.setPageSize(10);
        searchButton.addClickListener(click -> {
            try{
                fetchLocations(filter.getValue(), 1);
            }
            catch (IOException e){
                e.printStackTrace();
                Notification.show("An error occurred while fetching location data. Please try again.");
            }
        });
    }
    private void fetchLocations(String cityName, int page) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.weatherapi.com/v1/locations.json?key=YOUR_API_KEY&q=" + cityName + "&page=" + page)
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful() && response.body() != null){
            String responseBody = response.body().string();
            JSONArray json = new JSONArray(responseBody);
            grid.setItems(json.toList().stream().map(o -> new JSONObject((Map) o)));
        }else{
            String errorMessage = "Failed to fetch location data. HTTP Status Code: " + response.code();
            Notification.show(errorMessage);
        }
    }
}
