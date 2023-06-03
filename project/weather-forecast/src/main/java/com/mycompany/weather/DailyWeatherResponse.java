package com.mycompany.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyWeatherResponse {
    @JsonProperty("daily")
    private DailyWeatherForecast dailyWeatherForecast;
}
