package unit.avajlauncher;

public class JetPlane extends Aircraft implements Flayable
{
    private WeatherTower weatherTower;
    public JetPlane(String name, Coordinates coordinates, Logger logger){
        super(name, coordinates, logger);
    }
    public void updateCondition()
    {
        if (weatherTower.getWeather(this.coordinates).equals("SUN"))
        {
            logger.logInfo("JetPlane#" + this.name + "(" + this.id + ")" + " It is hot here like in th hell.");
            coordinates.setLatitude(coordinates.getLatitude() + 10);
            coordinates.setHeight(coordinates.getHeight() + 2);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("RAIN"))
        {
            logger.logInfo("JetPlane#" + this.name + "(" + this.id + ")" + "Rain, rain, rain :(");
            coordinates.setLatitude(coordinates.getLatitude() + 5);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("FOG"))
        {
            logger.logInfo("JetPlane#" + this.name + "(" + this.id + ")" + " I am in fog you will never find me.");
            coordinates.setLatitude(coordinates.getLatitude() + 1);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("SNOW"))
        {
            logger.logInfo("JetPlane#" + this.name + "(" + this.id + ")" + "New year atmosphere outside");
            coordinates.setHeight(coordinates.getHeight() - 7);
        }
        if (coordinates.getHeight() <= 0)
        {
            logger.logInfo("JetPlane#" + this.name + "(" + this.id + ")" + " landing.");
            logger.logInfo("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower and is landed.");
            weatherTower.unregister(this);
        }
    }
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        logger.logInfo("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}
