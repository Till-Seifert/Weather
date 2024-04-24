package com.oocode.teamoptimization;

import java.time.DayOfWeek;
import java.util.Map;


public class CachingForecaster implements Forecaster {
    private final Forecaster delegate;
    private String latestPlace;
    private DayOfWeek latestDay;
    private Forecast latestForecast;


    public CachingForecaster(Forecaster delegate) {
        this.delegate = delegate;
    }

    @Override
    public Forecast forecastFor(String place, DayOfWeek dayOfWeek){
        if(this.latestForecast == null){
            this.latestForecast = this.delegate.forecastFor(place, dayOfWeek);
            this.latestPlace = place;
            this.latestDay = dayOfWeek;
        } else if (!place.equals(this.latestPlace) || !dayOfWeek.equals(this.latestDay)) {
            this.latestForecast = this.delegate.forecastFor(place, dayOfWeek);
            this.latestPlace = place;
            this.latestDay = dayOfWeek;
        }
        return latestForecast;
    }
}
