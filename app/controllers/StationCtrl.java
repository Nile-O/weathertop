package controllers;

import java.util.List;

import models.Station;
import models.Reading;

import java.lang.Math;

import java.util.List;
import static models.Reading.windText;
import static models.Reading.weatherText;
import static models.Reading.windDirectionText;

import play.Logger;

import play.mvc.Controller;

import static models.Reading.*;

public class StationCtrl extends Controller
{
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        List<Station> stations = Station.findAll();

        for(Station a : stations) {
            if(a.readings.size() !=0) {
                a.weatherCode = weatherText(a.readings.get(a.readings.size() - 1).code);

                a.latestTemp = a.readings.get(a.readings.size() - 1).temperature;

                a.latestWind = windText(a.readings.get(a.readings.size() - 1).windSpeed);

                a.latestPressure = a.readings.get(a.readings.size() - 1).pressure;

                a.latestFTemp = (a.latestTemp * 9 / 5) + 32;

                a.latestWindSpeed = a.readings.get(a.readings.size() - 1).windSpeed;

                a.latestChill = Math.round((13.12 + (0.6215 * a.latestTemp) - (11.37 * (Math.pow(a.latestWindSpeed, 0.16))) + ((0.3965 * a.latestTemp) * (Math.pow(a.latestWindSpeed, 0.16)))) * 100.0) / 100.0;

                a.windCompass = windDirectionText(a.readings.get(a.readings.size() - 1).windDirection);

                a.minTemp = getMinTemp(a.readings);

                a.maxTemp = getMaxTemp(a.readings);

                a.minWind = getMinWind(a.readings);

                a.maxWind = getMaxWind(a.readings);

                a.maxPressure = getMaxPressure(a.readings);

                a.minPressure = getMinPressure(a.readings);

            }
        }

        Logger.info("Station id = " + id);
        render("station.html", station, stations);
    }

    public static void addReading(Long id, int code, double temperature, double windSpeed, int pressure, int windDirection)
    {
        Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }
}
