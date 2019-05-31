package unit.avajlauncher;

class Aircraft
{
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter;
    protected Logger logger;
    public Aircraft(String name, Coordinates coordinates, Logger logger)
    {
        this.name = name;
        this.coordinates = coordinates;
        id = nextId();
        this.logger = logger;
    }
    private long nextId(){ return idCounter++; }
}
