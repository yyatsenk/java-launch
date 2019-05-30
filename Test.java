package unit.avajlauncher;

import java.util.*;
import java.io.*;

interface Flayable
{
    public abstract void updateCondition();
    public abstract void registerTower(WeatherTower weatherTower);
}

class Tower
{
    private ArrayList<Flayable> observers = new ArrayList<Flayable>();
    private ArrayList<Flayable> toRemove = new ArrayList<Flayable>();
    public void register(Flayable flyable)
    {
        observers.add(flyable);
        flyable.registerTower((WeatherTower)this);
    }
    public void unregister(Flayable flyable)
    {
        toRemove.add(flyable);
    }
    protected void conditionChanged()
    {
        for(Flayable ob: observers)
            ob.updateCondition();
        observers.removeAll(toRemove);
    }
}

class WeatherTower extends Tower
{
    public String getWeather(Coordinates coordinates)
    {
        String weather;
        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        weather = weatherProvider.getCurrentWeather(coordinates);
        return weather;
    }
    private void changeWeather()
    {

    }
}

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
        if (coordinates.getHeight() < 25)
            return weather[0];
        if (coordinates.getHeight() < 50)
            return weather[1];
        if (coordinates.getHeight() < 75)
            return weather[2];
        return weather[3];
    }
}

class Coordinates
{
    private int longitude;
    private int latitude;
    private int height;
    public Coordinates(int longitude, int latitude, int height)
    {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }
    public int getLongitude(){ return longitude; }
    public int getLatitude(){ return latitude; }
    public int getHeight(){ return height; }
    public void setLongitude(int longitude)
    {   
        if (longitude > 100)
            this.longitude = 100;
        else if (longitude < 0)
            this.longitude = 0;
        else
            this.longitude = longitude;
    }
    public void setLatitude(int latitude)
    {
        if (latitude > 100)
            this.latitude = 100;
        else if (latitude < 0)
            this.latitude = 0;
        else
            this.latitude = latitude;
    }
    public void setHeight(int height)
    { 
        this.height = height > 100 ? 100 : height;
    }
}

class Aircraft
{
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter;
    public Aircraft(String name, Coordinates coordinates)
    {
        this.name = name;
        this.coordinates = coordinates;
        id = nextId();
    }
    private long nextId(){ return idCounter++; }
}

class Helicopter extends Aircraft implements Flayable
{
    private WeatherTower weatherTower;
    public Helicopter(String name, Coordinates coordinates){
        super(name, coordinates);
    }
    public void updateCondition()
    {
        if (weatherTower.getWeather(this.coordinates).equals("SUN"))
        {
            System.out.println("Helicopter#" + this.name + "(" + this.id + ")" + " Sunny day all cool");
            coordinates.setLongitude(coordinates.getLongitude() + 10);
            coordinates.setHeight(coordinates.getHeight() + 2);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("RAIN"))
        {
            System.out.println("Helicopter#" + this.name + "(" + this.id + ")" + " Rain is bad");
            coordinates.setLongitude(coordinates.getLongitude() + 5);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("FOG"))
        {
            System.out.println("Helicopter#" + this.name + "(" + this.id + ")" + " Foggy here, i see nothing");
            coordinates.setLongitude(coordinates.getLongitude() + 1);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("SNOW"))
        {
            System.out.println("Helicopter#" + this.name + "(" + this.id + ")" + " Snowww nicee");
            coordinates.setHeight(coordinates.getHeight() - 12);
        }
        if (coordinates.getHeight() <= 0)
        {
            System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower and is landed.");
            weatherTower.unregister(this);
        }
    }
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}

class JetPlane extends Aircraft implements Flayable
{
    private WeatherTower weatherTower;
    public JetPlane(String name, Coordinates coordinates){
        super(name, coordinates);
    }
    public void updateCondition()
    {
        if (weatherTower.getWeather(this.coordinates).equals("SUN"))
        {
            System.out.println("JetPlane#" + this.name + "(" + this.id + ")" + " Sunny day all cool");
            coordinates.setLatitude(coordinates.getLatitude() + 10);
            coordinates.setHeight(coordinates.getHeight() + 2);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("RAIN"))
        {
            System.out.println("JetPlane#" + this.name + "(" + this.id + ")" + " Rain is bad");
            coordinates.setLatitude(coordinates.getLatitude() + 5);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("FOG"))
        {
            System.out.println("JetPlane#" + this.name + "(" + this.id + ")" + " Foggy here, i see nothing");
            coordinates.setLatitude(coordinates.getLatitude() + 1);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("SNOW"))
        {
            System.out.println("JetPlane#" + this.name + "(" + this.id + ")" + " Snowww nicee");
            coordinates.setHeight(coordinates.getHeight() - 7);
        }
        if (coordinates.getHeight() <= 0)
        {
            System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower and is landed.");
            weatherTower.unregister(this);
        }
    }
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}

class Baloon extends Aircraft implements Flayable
{
    private WeatherTower weatherTower;
    public Baloon(String name, Coordinates coordinates){
        super(name, coordinates);
    }
    public void updateCondition()
    {
        if (weatherTower.getWeather(this.coordinates).equals("SUN"))
        {
            System.out.println("Baloon#" + this.name + "(" + this.id + ")" + " Sunny day all cool");
            coordinates.setLongitude(coordinates.getLongitude() + 2);
            coordinates.setHeight(coordinates.getHeight() + 4);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("RAIN"))
        {
            System.out.println("Baloon#" + this.name + "(" + this.id + ")" + " Rain is bad");
            coordinates.setHeight(coordinates.getHeight() - 5);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("FOG"))
        {
            System.out.println("Baloon#" + this.name + "(" + this.id + ")" + " Foggy here, i see nothing");
            coordinates.setHeight(coordinates.getHeight() - 3);
        }
        else if (weatherTower.getWeather(this.coordinates).equals("SNOW"))
        {
            System.out.println("Baloon#" + this.name + "(" + this.id + ")" + " Snowww nicee");
            coordinates.setHeight(coordinates.getHeight() - 15);
        }
        if (coordinates.getHeight() <= 0)
        {
            System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower and is landed.");
            weatherTower.unregister(this);
        }
    }
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}

class AircraftFactory
{
    public Flayable newAircraft(String type, String name, int longitude, int latitude, int height)
    {
        Coordinates coords = new Coordinates(longitude, latitude, height);
        if (type.equals("Helicopter"))
            return new Helicopter(name, coords);
        else if (type.equals("JetPlane"))
            return new JetPlane(name, coords);
        else if (type.equals("Baloon"))
            return new Baloon(name, coords);
        else
        {
            System.out.println("ERROR");
            return null;
        }
    }
}

public class Test
{
    private static List<String> readFile(String filename)
    {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            return records;
    }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] arg)
    {
        boolean i = true;
        int iterations = 0;
        AircraftFactory factory = new AircraftFactory();
        WeatherTower mainTower = new WeatherTower();
        WeatherProvider weatherProvider; 
        List<String> read = readFile("/home/yyatsenko/unit/avajlauncher/scenario");
        
        weatherProvider = WeatherProvider.getProvider();
        Flayable tmp;
        for (String line: read)
        {
            if (i)
            {
                iterations = Integer.parseInt(line);
                i = false;
            }
            else
            {
                String[] parts = line.split(" ");
                if (parts.length != 5)
                {
                    System.out.println("Error");
                    return;
                }
                //System.out.println(parts[0] + " " + parts[1] + " " + Integer.parseInt(parts[2]) + " " + Integer.parseInt(parts[3]) + " " + Integer.parseInt(parts[4]) );
                tmp = factory.newAircraft(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                mainTower.register(tmp);
            }
        }
        
        for(int j = 0; j < iterations; j++)
        {
            mainTower.conditionChanged();
        }
        System.out.println(iterations);
    }
}
