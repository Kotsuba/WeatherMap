package com.kotsuba.weathermap.core.entity;

import com.kotsuba.weathermap.core.WeatherMapException;

import java.io.*;
import java.util.*;

/**
 * Created by Kotsuba on 14.06.2016.
 */
public class Favorites implements Serializable {
    private final File file = new File("favorites.dat");
    public static Favorites favorites;
    private Set<String> countryList = new LinkedHashSet<String>();

    private Favorites() {
    }

    public static Favorites getInstanse() throws WeatherMapException {
        if (favorites == null) {
            favorites = new Favorites();
            favorites.readFavoritesList();
        }
        return favorites;
    }

    public Favorites(String country) {
        this.countryList.add(country);
    }

    public Set<String> getCountryList() {
        return countryList;
    }

    public void addCountry(String country) throws WeatherMapException {
        this.countryList.add(country);
        writeFavoritesList();
    }

    private void readFavoritesList() throws WeatherMapException {
        if (file.exists()) {
            if (file.length() != 0) {
                try{
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                    favorites = (Favorites) in.readObject();
                    file.delete();
                } catch (IOException | ClassNotFoundException ex) {
                    throw new WeatherMapException("Ошибка");
                }
            }
        }
    }

    private void writeFavoritesList() throws WeatherMapException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        } catch (IOException ex) {
            throw new WeatherMapException("Ошибка");
        }
    }
}
