package unit.avajlauncher;

import java.util.*;

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

public class WeatherTower extends Tower
{
    public String getWeather(Coordinates coordinates)
    {
        String weather;
        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        weather = weatherProvider.getCurrentWeather(coordinates);
        return weather;
    }
    private void changeWeather(){}
}