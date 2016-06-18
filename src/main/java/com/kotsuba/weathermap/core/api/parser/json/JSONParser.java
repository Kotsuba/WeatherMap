package com.kotsuba.weathermap.core.api.parser.json;

import com.kotsuba.weathermap.core.WeatherMapException;
import com.kotsuba.weathermap.core.entity.WeatherInfo;
import com.kotsuba.weathermap.core.entity.Temperature;
import com.kotsuba.weathermap.core.api.parser.Parser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kotsuba on 09.06.2016.
 */
public class JSONParser implements Parser {
    JSONObject jsonObject = null;
    WeatherInfo weatherInfo = null;
    Temperature temperature = null;

    public List<WeatherInfo> getToday(String response) throws WeatherMapException {
        List<WeatherInfo> infoList = new ArrayList<WeatherInfo>();
        temperature = new Temperature();
        weatherInfo = new WeatherInfo();
        try {
            jsonObject = new JSONObject(response);
            if (isValidCountry(jsonObject)) {
                JSONArray weather = jsonObject.getJSONArray("weather");
                JSONObject data = weather.getJSONObject(0);
                weatherInfo.setWheater(data.getString("description"));
                weatherInfo.setIcon(data.getString("icon"));
                JSONObject main = jsonObject.getJSONObject("main");
                temperature.setDay(main.getDouble("temp"));
                weatherInfo.setTemperature(temperature);
                weatherInfo.setPressure(main.getDouble("pressure"));
                weatherInfo.setHumidity(main.getDouble("humidity"));
                JSONObject wind = jsonObject.getJSONObject("wind");
                weatherInfo.setSpeedWind(wind.getDouble("speed"));

                weatherInfo.setTime(new Date(jsonObject.getLong("dt") * 1000));
                weatherInfo.setCountry(jsonObject.getString("name"));
                infoList.add(weatherInfo);
            }
            return infoList;
        }catch (JSONException ex){
            throw new WeatherMapException("Ошибка получения данных");
        }
    }

    public List<WeatherInfo> getDays5(String response) throws WeatherMapException {
        List<WeatherInfo> infoList = new ArrayList<WeatherInfo>();
        weatherInfo = new WeatherInfo();
        temperature = new Temperature();

        try {
            jsonObject = new JSONObject(response);
            if (isValidCountry(jsonObject)) {
                JSONObject city = jsonObject.getJSONObject("city");
                weatherInfo.setCountry(city.getString("name"));

                JSONArray list = jsonObject.getJSONArray("list");
                for (int i = 0; i < list.length(); i++) {
                    JSONObject object = list.getJSONObject(i);
                    weatherInfo.setTime(new Date(object.getLong("dt") * 1000));

                    JSONObject main = object.getJSONObject("main");
                    temperature.setDay(main.getDouble("temp"));
                    weatherInfo.setTemperature(temperature.clone());
                    weatherInfo.setPressure(main.getDouble("pressure"));
                    weatherInfo.setHumidity(main.getDouble("humidity"));
                    JSONArray weatherArray = object.getJSONArray("weather");
                    JSONObject weather = weatherArray.getJSONObject(0);
                    weatherInfo.setWheater(weather.getString("description"));
                    weatherInfo.setIcon(weather.getString("icon"));
                    JSONObject wind = object.getJSONObject("wind");
                    weatherInfo.setSpeedWind(wind.getDouble("speed"));
                    infoList.add(weatherInfo.clone());
                }
            }
            return infoList;
        }catch (JSONException ex){
            throw new WeatherMapException("Ошибка получения данных");
        }
    }

    public List<WeatherInfo> getDays16(String response) throws WeatherMapException {
        List<WeatherInfo> infoList = new ArrayList<WeatherInfo>();
        weatherInfo = new WeatherInfo();
        temperature = new Temperature();

        try {
            jsonObject = new JSONObject(response);
            if (isValidCountry(jsonObject)) {
                JSONObject city = jsonObject.getJSONObject("city");
                weatherInfo.setCountry(city.getString("name"));
                JSONArray list = jsonObject.getJSONArray("list");
                for (int i = 0; i < list.length(); i++) {
                    JSONObject object = list.getJSONObject(i);
                    JSONObject temp = object.getJSONObject("temp");
                    temperature.setDay(temp.getDouble("day"));
                    temperature.setNight(temp.getDouble("night"));
                    temperature.setEve(temp.getDouble("eve"));
                    temperature.setMorn(temp.getDouble("morn"));
                    weatherInfo.setTemperature(temperature.clone());
                    weatherInfo.setTime(new Date(object.getLong("dt") * 1000));
                    weatherInfo.setPressure(object.getDouble("pressure"));
                    weatherInfo.setHumidity(object.getDouble("humidity"));
                    JSONArray weatherArray = object.getJSONArray("weather");
                    JSONObject weather = weatherArray.getJSONObject(0);
                    weatherInfo.setWheater(weather.getString("description"));
                    weatherInfo.setIcon(weather.getString("icon"));
                    weatherInfo.setSpeedWind(object.getDouble("speed"));
                    infoList.add(weatherInfo.clone());
                }
            }
            return infoList;
        }catch (JSONException ex){
            throw new WeatherMapException("Ошибка получения данных");
        }
    }

    private boolean isValidCountry(JSONObject object) throws WeatherMapException {
        if (object.getInt("cod") == 404) {
            throw new WeatherMapException("Введенный город не найден");
        }
        return true;
    }
}
