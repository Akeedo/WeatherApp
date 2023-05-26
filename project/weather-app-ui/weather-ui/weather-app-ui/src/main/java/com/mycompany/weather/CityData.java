package com.mycompany.weather;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.math.BigDecimal;

@Getter
@Setter
public class CityData {
    private String city;
    private String longitude;
    private String latitude;
    private String country;
    private String state;

    public CityData(JSONObject jsonObject){
        BigDecimal latDecimal = jsonObject.getBigDecimal("lat");
        this.latitude = latDecimal.toString();
        BigDecimal longitudeDecimal = jsonObject.getBigDecimal("lon");
        this.longitude = longitudeDecimal.toString();
        this.city   = jsonObject.getString("name");
        this.country = jsonObject.getString("country");
        this.state = jsonObject.getString("state");
    }
}
