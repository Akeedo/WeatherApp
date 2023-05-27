package com.mycompany.weather;

import lombok.Getter;
import lombok.Setter;
import org.atmosphere.config.service.Get;

import java.util.List;

@Getter
@Setter
public class CityResponse {
    private List<City> results;
    private double generationTimeMS;
}
