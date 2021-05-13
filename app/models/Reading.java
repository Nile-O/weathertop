package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.List;


@Entity
public class Reading extends Model {
    public int code;
    public double temperature;
    public double windSpeed;
    public int pressure;
    public int windDirection;


    /**
     * Construct a Reading - the values for the weather parameters
     */
    public Reading(int code, double temperature, double windSpeed, int pressure, int windDirection) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
    }

    public int getCode() {
        return code;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public static String weatherText(int code) {
        String result = " ";
        switch (code) {
            case 100:
                result = "Clear";
                break;
            case 200:
                result = "Partial clouds";
                break;
            case 300:
                result = "Cloudy";
                break;
            case 400:
                result = "Light Showers";
                break;
            case 500:
                result = "Heavy Showers";
                break;
            case 600:
                result = "Rain";
                break;
            case 700:
                result = "Snow";
                break;
            case 800:
                result = "Thunder";
                break;
        }
        return result;
    }


    public static double windText(double windSpeed) {
        if (windSpeed <= 1) {
            return 0;
        } else if (windSpeed > 1 && windSpeed <= 5) {
            return 1;
        } else if (windSpeed >= 6 && windSpeed <= 11) {
            return 2;
        } else if (windSpeed >= 12 && windSpeed <= 19) {
            return 3;
        } else if (windSpeed >= 20 && windSpeed <= 28) {
            return 4;
        } else if (windSpeed >= 29 && windSpeed <= 38) {
            return 5;
        } else if (windSpeed >= 39 && windSpeed <= 49) {
            return 6;
        } else if (windSpeed >= 50 && windSpeed <= 61) {
            return 7;
        } else if (windSpeed >= 62 && windSpeed <= 74) {
            return 8;
        } else if (windSpeed >= 75 && windSpeed <= 88) {
            return 9;
        } else if (windSpeed >= 89 && windSpeed <= 102) {
            return 10;
        } else
            return 11;
    }


    public static String windDirectionText(double windDirection) {
        String result = "";
        if (windDirection >= 11.25 && windDirection <= 33.75) {
            result = "North North East";
        } else if (windDirection >= 33.75 && windDirection <= 56.25) {
            result = "North East";
        } else if (windDirection >= 56.25 && windDirection <= 78.75) {
            result = "East North East";
        } else if (windDirection >= 78.75 && windDirection <= 101.25) {
            result = "East";
        } else if (windDirection >= 101.25 && windDirection <= 123.75) {
            result = "East South East";
        } else if (windDirection >= 123.75 && windDirection <= 146.25) {
            result = "South East";
        } else if (windDirection >= 146.25 && windDirection <= 168.75) {
            result = "South South East";
        } else if (windDirection >= 168.75 && windDirection <= 191.25) {
            result = "South";
        } else if (windDirection >= 191.25 && windDirection <= 213.75) {
            result = "South South West";
        } else if (windDirection >= 213.75 && windDirection <= 236.25) {
            result = "South West";
        } else if (windDirection >= 236.25 && windDirection <= 258.75) {
            result = "West South West";
        } else if (windDirection >= 258.75 && windDirection <= 281.25) {
            result = "West";
        } else if (windDirection >= 281.25 && windDirection <= 303.75) {
            result = "West North West";
        } else if (windDirection >= 303.75 && windDirection <= 326.25) {
            result = "North West";
        } else if (windDirection >= 326.25 && windDirection <= 348.75) {
            result = "North North West";
        } else if (windDirection <= 11.25) {
            result = "North";
        } else if (windDirection >= 348.75 && windDirection <= 360.00) {
            result = "North";
        }
        return result;
    }

    public static double getMinTemp(List<Reading> readings)
    {
        Reading minTempReading = null;
        if (readings.size() >0){
            minTempReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature < minTempReading.temperature) {
                    minTempReading = reading;
                }
            }

        } return minTempReading.temperature;
    }

    public static double getMaxTemp(List<Reading> readings)
    {
        Reading maxTemp = null;
        if (readings.size() >0){
            maxTemp = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature > maxTemp.temperature) {
                    maxTemp = reading;
                }
            }

        } return maxTemp.temperature;
    }

    public static double getMinWind(List<Reading> readings)
    {
        Reading minWind = null;
        if (readings.size() >0){
            minWind = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed < minWind.windSpeed) {
                    minWind = reading;
                }
            }

        } return minWind.windSpeed;
    }

    public static double getMaxWind(List<Reading> readings)
    {
        Reading maxWind = null;
        if (readings.size() >0){
            maxWind = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed > maxWind.windSpeed) {
                    maxWind = reading;
                }
            }

        } return maxWind.windSpeed;
    }

    public static int getMaxPressure(List<Reading> readings)
    {
        Reading maxPressure = null;
        if (readings.size() >0){
            maxPressure = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure > maxPressure.pressure) {
                    maxPressure = reading;
                }
            }

        } return maxPressure.pressure;
    }

    public static int getMinPressure(List<Reading> readings)
    {
        Reading minPressure = null;
        if (readings.size() >0){
            minPressure = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure < minPressure.pressure) {
                    minPressure = reading;
                }
            }

        } return minPressure.pressure;
    }


}

