package com.mycompany.weather;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {

    public MainView() {
        Button button = new Button("Get Weather",
            click -> {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                        .url("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true&hourly=temperature_2m,relativehumidity_2m,windspeed_10m")
                        .build();
                    Response response = client.newCall(request).execute();

                    if(response.isSuccessful() && response.body() != null){
                        String responseBody = response.body().string();
                        JSONObject json = new JSONObject(responseBody);
                        String forecast = json.getJSONObject("current_weather").get("temperature").toString();
                        Notification.show("The current temperature in London is: " + forecast +" *C");
                    } else{
                        String errorMessage = "Failed to fetch weather data. HTTP Status Code: " + response.code();
                        Notification.show(errorMessage);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Notification.show("An error occurred while fetching weather data. Please try again.");
                }
            });
        add(button);
    }
}
