package controllers;
import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.List;
import java.util.ArrayList;

import static models.Reading.*;


public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
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
    render ("dashboard.html", stations);

  }

  public static void addStation (String name, double latitude, double longitude)
  {
    Logger.info("Adding a station");
    Member member = Accounts.getLoggedInMember();
    Station station = new Station (name, latitude, longitude);
    member.stations.add(station);
    member.save();
    redirect("/dashboard");
  }
}

