package unit.avajlauncher;

import java.io.*;

public class Logger
{
    private FileWriter nFile;
    public Logger(String filename)
    {
        try
        {
            nFile = new FileWriter(filename);
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(0);
        }
    }
    public void logInfo(String msg)
    {
        try
        {
            nFile.write(msg + "\n");
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(0);
        }
    }
    public void closeFile()
    {
        try
        {
            nFile.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(0);
        }
    }
}