package com.kotsuba.weathermap.core.api.parser;

import com.kotsuba.weathermap.core.WeatherMapException;
import com.kotsuba.weathermap.core.entity.WeatherInfo;

import java.util.List;

/**
 * Created by Kotsuba on 09.06.2016.
 */
public interface Parser {
    List<WeatherInfo> getToday(String response)throws WeatherMapException;
    List<WeatherInfo>getDays5(String response)throws WeatherMapException;
    List<WeatherInfo>getDays16(String response)throws WeatherMapException;
}
