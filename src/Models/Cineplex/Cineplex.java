package Models.Cineplex;

import Utilities.Entity;
import java.util.ArrayList;

/**
 * Class that represents a Cineplex that comprises of a group of cinemas
 * @author Soggeh
 */
public class Cineplex extends Entity {
    
    private static final long serialVersionUID = 12324134119L;
    
    /**
     * The list of cinema that is within this Cineplex
     */
    private ArrayList<Cinema> cinemas;
    
    /**
     * The location of the Cineplex
     */
    protected String location;
    
    /**
     * Create a new Cineplex with multiple cinemas
     * @param location The location of the Cineplex
     * @param args Cinemas to be included in the cineplex
     */
    public Cineplex(String location,Cinema... args) {
        cinemas = new ArrayList<>();
        for (Cinema arg : args) {
            cinemas.add(arg);
        }
        this.location = location;
    }
    
    /**
     * Create a new empty Cineplex
     * @param location The location of the Cineplex
     */
    public Cineplex(String location) {
        cinemas = new ArrayList<>();
        this.location = location;
    }
    
    /**
     * Add a new cinema to this Cineplex
     * @param cinema
     */
    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }
    
    /**
     * Get cinemas of the cineplex
     * @return The list of cinemas
     */
    public ArrayList<Cinema> getCinemas() {
        return this.cinemas;
    }
    
    /**
     * Get location of cineplex
     * @return the location of cineplex
     */
    public String getLocation() {
        return this.location;
    }
    
}
