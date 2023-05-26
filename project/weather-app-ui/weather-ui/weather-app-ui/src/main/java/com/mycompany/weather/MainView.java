package com.mycompany.weather;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {

    private void fetchLocations(String cityName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.openweathermap.org/geo/1.0/direct?q="+cityName+"&limit=5&appid=e6e1309d61b8430fe739ae6b30ff3dd2"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String responseBody = response.body();
            JSONArray json = new JSONArray(responseBody);
            grid.setItems(json.toList().stream().map(o -> new WeatherData(new JSONObject((Map) o))));
        } else {
            String errorMessage = "Failed to fetch location data. HTTP Status Code: " + response.statusCode();
            Notification.show(errorMessage);
        }
    }
}
