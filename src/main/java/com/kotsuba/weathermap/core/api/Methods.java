package com.kotsuba.weathermap.core.api;

import com.kotsuba.weathermap.core.entity.WeatherInfo;

/**
 * Created by Kotsuba on 09.06.2016.
 */
public class Methods {
    public static final String PROTOCOL="http://";
    public static final String API=PROTOCOL+"api.openweathermap.org";
    public static final String DATA="/data/2.5";
    public static final String IMGPATH="/img/w";
    public static final String IMGEXTENSION=".png";
    public static final String APPID="&APPID=b704277bddb734e0f9c22b0fc3057154";
    public static final String LANGUAGE="&lang=ru";
    public static final String UNITS="&units=metric";
    public static final String DAYS16=API+DATA+"/forecast/daily?q=%s&cnt=16"+UNITS+LANGUAGE+APPID;
    public static final String DAYS5=API+DATA+"/forecast?q=%s"+UNITS+LANGUAGE+APPID;
    public static final String TODAY=API+DATA+"/weather?q=%s"+UNITS+LANGUAGE+APPID;
    public static final String IMG=API+IMGPATH+"/%s"+IMGEXTENSION;

    public static String getImgPath(WeatherInfo weatherInfo){
        return String.format(IMG,weatherInfo.getIcon());
    }

}
