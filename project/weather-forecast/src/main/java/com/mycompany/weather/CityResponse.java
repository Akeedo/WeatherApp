package com.mycompany.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CityResponse {
    private List<City> results;
    private double generationTimeMS;
}
