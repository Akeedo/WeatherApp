package com.mycompany.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HourlyWeatherResponse {
    private HourlyWeatherForecast hourly;
}
