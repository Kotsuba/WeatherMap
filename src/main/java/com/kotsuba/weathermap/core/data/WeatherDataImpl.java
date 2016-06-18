package com.kotsuba.weathermap.core.data;

import com.kotsuba.weathermap.core.WeatherMapException;
import com.kotsuba.weathermap.core.entity.WeatherInfo;
import com.kotsuba.weathermap.core.api.HTTPRequest;
import com.kotsuba.weathermap.core.api.Methods;
import com.kotsuba.weathermap.core.api.parser.Parser;
import com.kotsuba.weathermap.core.api.parser.json.JSONParser;

import java.io.IOException;
import java.util.List;

/**
 * Created by Kotsuba on 09.06.2016.
 */
public class WeatherDataImpl implements WeatherData {
    HTTPRequest request=new HTTPRequest();
    Parser parser=new JSONParser();

    public List<WeatherInfo> getToday(String country)throws WeatherMapException {
        String url=String.format(Methods.TODAY,country);
        String response=request.doGet(url);
        System.out.println(url);
        return parser.getToday(response);
    }

    public List<WeatherInfo> getDays5(String country)throws WeatherMapException {
        String url=String.format(Methods.DAYS5,country);
        String response=request.doGet(url);
        return parser.getDays5(response);
    }

    public List<WeatherInfo> getDays16(String country)throws WeatherMapException {
        String url=String.format(Methods.DAYS16,country);
        String response=request.doGet(url);
        return parser.getDays16(response);
    }
}
