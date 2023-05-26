package com.mycompany.weather;

import org.json.JSONObject;

import java.math.BigDecimal;

public class WeatherData {
    private String location;

    public WeatherData(JSONObject jsonObject){
        BigDecimal latDecimal = jsonObject.getBigDecimal("lat");
        this.location = latDecimal.toString();
    }
    public String getLocation(){
        return location;
    }
}
