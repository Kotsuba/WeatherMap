package com.kotsuba.weathermap.core.entity;

/**
 * Created by Kotsuba on 12.06.2016.
 */
public class Temperature implements Cloneable {
    private double day;
    private double night;
    private double eve;
    private double morn;

    public Temperature(){}

    public Temperature(Temperature t){
        this.day=t.day;
        this.night=t.night;
        this.eve=t.eve;
        this.morn=t.morn;
    }

    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public double getNight() {
        return night;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public double getEve() {
        return eve;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public double getMorn() {
        return morn;
    }

    public void setMorn(double morn) {
        this.morn = morn;
    }

    @Override
    public String toString() {
        String temp="";
        if(day!=0){
            temp = temp.concat(String.valueOf(Math.round(day))+"°C");
        }
        if(morn!=0){
            temp=temp.concat(" \nУтром:"+String.valueOf(Math.round(morn))+"°C");
        }
        if(eve!=0){
            temp=temp.concat(" \nВечером:"+String.valueOf(Math.round(eve))+"°C");
        }
        if(night!=0){
            temp=temp.concat(" \nНочью:"+String.valueOf(Math.round(night))+"°C");
        }
        return temp;
    }

    @Override
    public Temperature clone()  {
        return new Temperature(this);
    }
}
