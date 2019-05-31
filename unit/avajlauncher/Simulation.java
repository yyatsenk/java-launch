package unit.avajlauncher;

import java.util.*;
import java.io.*;

public class Simulation
{
    private static List<String> readFile(String filename)
    {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int i = 0;
            
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            for (String record: records)
            {
                if (i == 0)
                {
                    if (!record.matches("^(\\d+)$"))
                        throw new InputError(i, "Bad number of iterations.");
                }
                else
                {
                    if (!record.matches("^(Baloon |JetPlane |Helicopter )(.+ )(\\d+ )(\\d+ )(\\d+)$"))
                        throw new InputError(i, "Bad string on the input.");
                }
                i++;
            }
            return records;
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.exit(0);
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
        if (arg.length != 1)
        {
            System.out.println("Usage: java simulations [filename]");
            System.exit(0);
        }
        Logger logger = new Logger("simulation.txt");
        List<String> read = readFile(arg[0]);
        
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
                tmp = factory.newAircraft(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), logger);
                mainTower.register(tmp);
            }
        }
        
        for(int j = 0; j < iterations; j++)
            mainTower.conditionChanged();
        logger.closeFile();
    }
}
