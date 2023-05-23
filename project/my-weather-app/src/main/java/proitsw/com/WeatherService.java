package proitsw.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WeatherService {

    private static final String API_URL = "https://api.open-meteo.com/v1/forecast";

    public String getWeatherDataByCity(String city) {
        String urlToRead = API_URL + "?city=" + city;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while calling weather API: " + e.getMessage());
        }
        return result.toString();
    }
}
