package com.mycompany.weather;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DailyWeather {
    private List<String> time;
    private List<Double> temperature2mMax;
    private List<Double> temperature2mMin;
    private List<Double> apparentTemperatureMax;
    private List<Double> rainSum;
    private List<Double> windSpeed10mMax;
}
