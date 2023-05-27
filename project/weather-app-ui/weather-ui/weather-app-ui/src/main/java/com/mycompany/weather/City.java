package com.mycompany.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
@NoArgsConstructor
public class City {
    private long id;
    private String name;
    private double latitude;
    private double longitude;
    private double elevation;
    private String featureCode;
    private String countryCode;
    private long admin1Id;
    private long admin2Id;
    private String timezone;
    private int population;
    private long countryId;
    private String country;
    private String admin1;
    private String admin2;


    public City(JSONObject jsonObject){
        this.id = jsonObject.has("id") ? jsonObject.getLong("id") : 0;
        this.name = jsonObject.has("name") ? jsonObject.getString("name") : "";
        this.latitude = jsonObject.has("latitude") ? jsonObject.getDouble("latitude") : 0.0;
        this.longitude = jsonObject.has("longitude") ? jsonObject.getDouble("longitude") : 0.0;
        this.elevation = jsonObject.has("elevation") ? jsonObject.getDouble("elevation") : 0.0;
        this.featureCode = jsonObject.has("feature_code") ? jsonObject.getString("feature_code") : "";
        this.countryCode = jsonObject.has("country_code") ? jsonObject.getString("country_code") : "";
        this.admin1Id = jsonObject.has("admin1_id") ? jsonObject.getLong("admin1_id") : 0;
        this.admin2Id = jsonObject.has("admin2_id") ? jsonObject.getLong("admin2_id") : 0;
        this.timezone = jsonObject.has("timezone") ? jsonObject.getString("timezone") : "";
        this.population = jsonObject.has("population") ? jsonObject.getInt("population") : 0;
        this.countryId = jsonObject.has("country_id") ? jsonObject.getLong("country_id") : 0;
        this.country = jsonObject.has("country") ? jsonObject.getString("country") : "";
        this.admin1 = jsonObject.has("admin1") ? jsonObject.getString("admin1") : "";
        this.admin2 = jsonObject.has("admin2") ? jsonObject.getString("admin2") : "";
    }
}
