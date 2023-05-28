package com.mycompany.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class WeatherDataDaily {
    private DailyWeather daily;

    public <T> List<T> jsonArrayToList(JSONArray jsonArray) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add((T) jsonArray.get(i));
        }
        return list;
        }

    }

