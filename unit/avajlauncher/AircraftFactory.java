package unit.avajlauncher;

public class AircraftFactory
{
    public Flayable newAircraft(String type, String name, int longitude, int latitude, int height, Logger logger)
    {
        Coordinates coords = new Coordinates(longitude, latitude, height);
        if (type.equals("Helicopter"))
            return new Helicopter(name, coords, logger);
        else if (type.equals("JetPlane"))
            return new JetPlane(name, coords, logger);
        else if (type.equals("Baloon"))
            return new Baloon(name, coords, logger);
        else
            return null;
    }
}