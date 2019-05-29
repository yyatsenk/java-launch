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
    private Flayable observers;
    public void register(Flayable flyable)
    {
        
    }
    public void unregister(Flayable flyable)
    {

    }
    protected void conditionChanged(){}
}

class WeatherTower extends Tower
{
    public String getWeather(Coordinates coordinates){ return new String();}
    private void changeWeather(){}
}

class WeatherProvider
{
    private WeatherProvider weatherProvider;
    String weather;
    private WeatherProvider(){}
    public WeatherProvider getProvider(){ return weatherProvider;}
    public String getCurrentWeather(Coordinates coordinates){return weather;}
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
}

class Aircraft
{
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private long idCounter;
    public Aircraft(String name, Coordinates coordinates)
    {
        this.name = name;
        this.coordinates = coordinates;
    }
    private long nextId(){ return ++this.id; }
}

class Helicopter extends Aircraft implements Flayable
{
    private WeatherTower weatherTower;
    public Helicopter(String name, Coordinates coordinates){
        super(name, coordinates);
    }
    public void updateCondition(){}
    public void registerTower(WeatherTower weatherTower){}
}

class JetPlane extends Aircraft implements Flayable
{
    private WeatherTower weatherTower;
    public JetPlane(String name, Coordinates coordinates){
        super(name, coordinates);
    }
    public void updateCondition(){}
    public void registerTower(WeatherTower weatherTower){}
}

class Baloon extends Aircraft implements Flayable
{
    private WeatherTower weatherTower;
    public Baloon(String name, Coordinates coordinates){
        super(name, coordinates);
    }
    public void updateCondition(){}
    public void registerTower(WeatherTower weatherTower){}
}

class AircraftFactory
{
    public Flayable newAircraft(String type, String name, int longitude, int latitude, int height)
    {
        Coordinates coords = new Coordinates(longitude, latitude, height);
        if (!(type == "Helicopter"))
            return new Helicopter(name, coords);
        else if (!(type == "JetPlane"))
            return new JetPlane(name, coords);
        else if (!(type == "Baloon"))
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
        ArrayList<Flayable> myList = new ArrayList<Flayable>();
        AircraftFactory factory = new AircraftFactory();
        Tower mainTower = new Tower();
        List<String> read = readFile("/home/yyatsenko/unit/avajlauncher/scenario");
        
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
                System.out.println(parts[0] + " " + parts[1] + " " + Integer.parseInt(parts[2]) + " " + Integer.parseInt(parts[3]) + " " + Integer.parseInt(parts[4]) );
                myList.add(factory.newAircraft(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                mainTower.register(myList.get(myList.size()-1));
            }
        }
        for(int j = 0; j < iterations; j++)
        {
            for (Flayable flyObject: myList)
                flyObject.updateCondition();
        }
        System.out.println(iterations);
    }
}
