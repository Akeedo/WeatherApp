package com.mycompany.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HourlyWeatherForecast {

    @JsonProperty("time")
    private List<String> time;

    @JsonProperty("temperature_2m")
    private List<Double> temperature2M;

    @JsonProperty("relativehumidity_2m")
    private List<Integer> relativeHumidity2M;

    @JsonProperty("rain")
    private List<Double> rain;

    @JsonProperty("windspeed_10m")
    private List<Double> windSpeed10M;
}
