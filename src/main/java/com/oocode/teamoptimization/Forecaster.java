package com.oocode.teamoptimization;

import java.time.DayOfWeek;

public interface Forecaster {
    record Forecast(int minTemp, int maxTemp, String description){};

    Forecast forecastFor(String place, DayOfWeek dayOfWeek);
}