package unit.avajlauncher;

public class Helicopter extends Aircraft implements Flayable
{
    private WeatherTower weatherTower;
    public Helicopter(String name, Coordinates coordinates, Logger logger){
        super(name, coordinates, logger);
    }
    public void updateCondition()
    {
        if (weatherTower.getWeather(this.coordinates).equals("SUN"))
        {
            logger.logInfo("Helicopter#" + this.name + "(" + this.id + ")" + " Sunny day all cool.");
            coordinates.setLongitude(coordinates.getLongitude() + 10);
            coordinates.setHeight(coordinates.getHeight() + 2);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("RAIN"))
        {
            logger.logInfo("Helicopter#" + this.name + "(" + this.id + ")" + " Rain helps to hide tears.");
            coordinates.setLongitude(coordinates.getLongitude() + 5);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("FOG"))
        {
            logger.logInfo("Helicopter#" + this.name + "(" + this.id + ")" + " Foggy here, i see nothing.");
            coordinates.setLongitude(coordinates.getLongitude() + 1);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("SNOW"))
        {
            logger.logInfo("Helicopter#" + this.name + "(" + this.id + ")" + " Snowww nicee.");
            coordinates.setHeight(coordinates.getHeight() - 12);
        }
        if (coordinates.getHeight() <= 0)
        {
            logger.logInfo("Helicopter#" + this.name + "(" + this.id + ")" + " landing.");
            logger.logInfo("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower and is landed.");
            weatherTower.unregister(this);
        }
    }
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        logger.logInfo("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}