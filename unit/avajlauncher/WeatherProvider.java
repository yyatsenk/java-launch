package unit.avajlauncher;

class WeatherProvider
{
    private static WeatherProvider weatherProvider = null;
    String weather[] = {"SUN", "RAIN", "FOG", "SNOW"};
    private WeatherProvider(){}
    public static WeatherProvider getProvider()
    {
        if (weatherProvider == null)
            return new WeatherProvider();
        return weatherProvider;
    }
    public String getCurrentWeather(Coordinates coordinates)
    {
        if (coordinates.getLongitude() > 50)
            return weather[3];
        if (coordinates.getHeight() < 25)
            return weather[0];
        if (coordinates.getHeight() < 50)
            return weather[1];
        if (coordinates.getHeight() < 75)
            return weather[2];
        return weather[3];
    }
}