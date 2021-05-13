package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Station extends Model {
    public String name;
    public double latitude;
    public double longitude;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    public String weatherCode;
    public double latestTemp;
    public double latestWind;
    public int latestPressure;
    public double latestFTemp;
    public double latestChill;
    public double latestWindSpeed;
    public String windCompass;
    public double minTemp;
    public double maxTemp;
    public double minWind;
    public double maxWind;
    public int maxPressure;
    public int minPressure;



    public Station(String name, double latitude, double longitude ) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
