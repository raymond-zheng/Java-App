/**
 * Created by raymondz on 2017-02-23.
 */
public class RunWeatherCSV {
    public static void main(String[] args) {
        WeatherCSV weatherCSV = new WeatherCSV();

        //weatherCSV.testFileWithColdestTemperatureReturn();

        //weatherCSV.testFileWithColdestTemperature();

        //works
        //weatherCSV.testColdestHourInFile();

        //works
        //weatherCSV.testLowestHumidityInFile();

        //
        //weatherCSV.testAverageTemperatureInFile();


        //
        weatherCSV.testAverageTemperatureWithHighHumidityInFile();

        //worked
        //weatherCSV.testLowestHumidityInManyFiles();
    }
}
