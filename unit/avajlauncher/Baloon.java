package unit.avajlauncher;

public class Baloon extends Aircraft implements Flayable
{
    private WeatherTower weatherTower;
    public Baloon(String name, Coordinates coordinates, Logger logger){
        super(name, coordinates, logger);
    }
    public void updateCondition()
    {
        if (weatherTower.getWeather(this.coordinates).equals("SUN"))
        {
            logger.logInfo("Baloon#" + this.name + "(" + this.id + ")" + "We need sunglasses.");
            coordinates.setLongitude(coordinates.getLongitude() + 2);
            coordinates.setHeight(coordinates.getHeight() + 4);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("RAIN"))
        {
            logger.logInfo("Baloon#" + this.name + "(" + this.id + ")" + "Rain is destroing our happiness.");
            coordinates.setHeight(coordinates.getHeight() - 5);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("FOG"))
        {
            logger.logInfo("Baloon#" + this.name + "(" + this.id + ")" + "We are in the middle of clouds.");
            coordinates.setHeight(coordinates.getHeight() - 3);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("SNOW"))
        {
            logger.logInfo("Baloon#" + this.name + "(" + this.id + ")" + "Cold and freezy snow is falling.");
            coordinates.setHeight(coordinates.getHeight() - 15);
        }
        if (coordinates.getHeight() <= 0)
        {
            logger.logInfo("Baloon#" + this.name + "(" + this.id + ")" + " landing.");
            logger.logInfo("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower and is landed.");
            weatherTower.unregister(this);
        }
    }
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        logger.logInfo("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}