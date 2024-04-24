package com.oocode;

import com.oocode.teamoptimization.Forecaster;
import com.oocode.teamoptimization.MetOfficeForecasterAdapter;

import java.io.IOException;
import java.time.DayOfWeek;

public class Example {
    public static void main(String[] args) throws IOException {
        String day = "Wednesday";
        String place = "Oxford";
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day.toUpperCase());
        Forecaster.Forecast forecast = new MetOfficeForecasterAdapter().forecastFor(place, dayOfWeek);
        System.out.printf(forecast.toString());
    }
}
