package controllers;
import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.List;
import java.util.ArrayList;


public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
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

