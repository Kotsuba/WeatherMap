package com.kotsuba.weathermap.core.data;

import com.kotsuba.weathermap.core.WeatherMapException;
import com.kotsuba.weathermap.core.entity.WeatherInfo;

import java.io.IOException;
import java.util.List;

/**
 * Created by Kotsuba on 09.06.2016.
 */
public interface WeatherData {
    List<WeatherInfo> getToday(String country)throws WeatherMapException;
    List<WeatherInfo> getDays5(String country)throws WeatherMapException;
    List<WeatherInfo>getDays16(String country)throws WeatherMapException;
}
