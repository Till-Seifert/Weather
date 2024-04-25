package com.oocode.teamoptimization;

import java.io.IOException;
import java.time.DayOfWeek;

public class NavyForecasterAdapter implements Forecaster{
    NavyForecastingClient forecaster = new NavyForecastingClient();

    @Override
    public Forecast forecastFor(String place, DayOfWeek dayOfWeek) {
        try {
            int minTemp = forecaster.min(dayOfWeek, place);
            int maxTemp = forecaster.max(dayOfWeek, place);
            String description = forecaster.desc(dayOfWeek, place);
            return new Forecaster.Forecast(minTemp, maxTemp, description);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
