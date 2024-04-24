package com.oocode;

import com.oocode.teamoptimization.CachingForecaster;
import com.oocode.teamoptimization.Forecaster;
import com.oocode.teamoptimization.Forecaster.Forecast;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static java.time.DayOfWeek.FRIDAY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CachingForecasterTest {
    @Test
    public void delegatesForecastingForNewForecast() {
        var delegate = mock(Forecaster.class);
        Forecast expectedForecast = new Forecast(1, 2, "cold");
        given(delegate.forecastFor("Oxford", FRIDAY)).willReturn(expectedForecast);

        var underTest = new CachingForecaster(delegate);

        var forecast = underTest.forecastFor("Oxford", FRIDAY);

        //verify(delegate).forecastFor("Oxford", FRIDAY); // This is arguable
        assertThat(forecast, equalTo(expectedForecast));
    }

    @Test
    public void providesSameOutputIfCalledTwice(){
        var delegate = mock(Forecaster.class);
        Forecast expectedForecast = new Forecast(12, 24, "mild");
        given(delegate.forecastFor("Oxford", FRIDAY)).willReturn(expectedForecast);

        var underTest = new CachingForecaster(delegate);

        var forecast = underTest.forecastFor("Oxford", FRIDAY);
        var secondForecast = underTest.forecastFor("Oxford", FRIDAY);
        verify(delegate, times(1)).forecastFor("Oxford", FRIDAY);

        assertThat(forecast, equalTo(secondForecast));
    }
    @Test
    public void providesDifferentOutputIfDifferentInput(){
        var delegate = mock(Forecaster.class);
        Forecast expectedForecast = new Forecast(12, 24, "mild");
        given(delegate.forecastFor("Oxford", FRIDAY)).willReturn(expectedForecast);
        Forecast expectedForecast2 = new Forecast(15, 20, "mild");
        given(delegate.forecastFor("London", FRIDAY)).willReturn(expectedForecast2);

        var underTest = new CachingForecaster(delegate);

        var forecast = underTest.forecastFor("Oxford", FRIDAY);
        var secondForecast = underTest.forecastFor("London", FRIDAY);
        verify(delegate, times(1)).forecastFor("Oxford", FRIDAY);
        verify(delegate, times(1)).forecastFor("London", FRIDAY);

        assertThat(forecast, equalTo(expectedForecast));
        assertThat(secondForecast, equalTo(expectedForecast2));
    }

}