package com.mycompany.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyWeather {
    private String time;
    private Double temperature2mMax;
    private Double temperature2mMin;
    private Double apparentTemperatureMax;
    private Double rainSum;
    private Double windSpeed10mMax;
}
