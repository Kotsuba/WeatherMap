package com.kotsuba.weathermap.core.api;

import com.kotsuba.weathermap.core.WeatherMapException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Kotsuba on 09.06.2016.
 */
public class HTTPRequest {
    private HttpURLConnection connection = null;
    private BufferedReader buffer = null;

    public String doGet(String api) throws WeatherMapException {
        try {
            connection = (HttpURLConnection) new URL(api).openConnection();
            buffer = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            return toStringFormat();
        } catch (IOException ex) {
            throw new WeatherMapException("Ошибка получения данных");
        } finally {
            try {
                connection.disconnect();
                buffer.close();
            } catch (IOException ex) {
                throw new WeatherMapException("Ошибка");
            }
            catch(NullPointerException ex){
                throw new WeatherMapException("Отсутствует интернет-соединение");
            }
        }
    }

    private String toStringFormat() throws WeatherMapException {
        try {
            String str;
            String response = "";
            while ((str = buffer.readLine()) != null) {
                response = str;
            }
            return response;
        } catch (IOException ex) {
            throw new WeatherMapException("Ошибка получения данных");
        }
    }

}
