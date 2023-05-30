package com.mycompany.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HourlyWeather {
    private String time;
    private Double temperature2M;
    private Integer relativeHumidity2M;
    private Double rain;
    private Double windSpeed10M;
}
