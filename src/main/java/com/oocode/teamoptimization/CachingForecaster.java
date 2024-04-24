package com.oocode.teamoptimization;

import java.time.DayOfWeek;

public class CachingForecaster implements Forecaster {
    private final Forecaster forcaster;

    public CachingForecaster(Forecaster delegate) {
        this.forcaster = delegate;
    }

    @Override
    public Forecast forecastFor(String place, DayOfWeek dayOfWeek){
        return this.forcaster.forecastFor(place, dayOfWeek);
    }
}
