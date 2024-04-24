package com.oocode.teamoptimization;

import java.io.IOException;
import java.time.DayOfWeek;

public class MetOfficeForecasterAdapter implements Forecaster {
    MetOfficeWeatherForecasterClient forecaster = new MetOfficeWeatherForecasterClient();

    @Override
    public Forecast forecastFor(String place, DayOfWeek dayOfWeek) {
        try {
            var forecast = forecaster.getForecast(place, dayOfWeek.getValue());
            return new Forecaster.Forecast(forecast.minTemp, forecast.maxTemp, forecast.description);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
