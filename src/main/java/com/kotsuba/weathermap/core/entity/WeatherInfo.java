package com.kotsuba.weathermap.core.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by Kotsuba on 09.06.2016.
 */
public class WeatherInfo implements Cloneable {
    private String wheater;
    private String icon;
    private Temperature temperature;
    private Double pressure;
    private Double humidity;
    private Double speedWind;
    private Date time;
    private String country;

    public WeatherInfo(){}

    public WeatherInfo(WeatherInfo weatherInfo) {
        this.wheater = weatherInfo.wheater;
        this.icon = weatherInfo.icon;
        this.temperature = weatherInfo.temperature;
        this.pressure = weatherInfo.pressure;
        this.humidity = weatherInfo.humidity;
        this.speedWind = weatherInfo.speedWind;
        this.time = weatherInfo.time;
        this.country = weatherInfo.country;
    }

    public String getWheater() {
        return wheater;
    }

    public void setWheater(String wheater) {
        this.wheater = wheater;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getSpeedWind() {
        return speedWind;
    }

    public void setSpeedWind(Double speedWind) {
        this.speedWind = speedWind;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM HH:mm");
        return   dateFormat.format(time)+"\n"+
                "Погода:"+wheater+"\n"+
                "Температура:"+temperature+"\n"+
                "Давление:"+Math.round(pressure*0.75)+"мм.рт.ст\n"+
                "Влажность:"+humidity+"%\n"+
                "Скорость ветра:"+Math.round(speedWind)+"м/с\n";
    }
    @Override
    public WeatherInfo clone(){
        return new WeatherInfo(this);
    }
}
