package com.mycompany.weather;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DailyWeatherForecast {
    @JsonProperty("time")
    private List<String> time;
    @JsonProperty("temperature_2m_max")
    private List<Double> temperature2mMax;
    @JsonProperty("temperature_2m_min")
    private List<Double> temperature2mMin;
    @JsonProperty("apparent_temperature_max")
    private List<Double> apparentTemperatureMax;
    @JsonProperty("rain_sum")
    private List<Double> rainSum;
    @JsonProperty("windspeed_10m_max")
    private List<Double> windSpeed10mMax;
}
