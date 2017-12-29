package Controllers;

import Models.Cineplex.*;
import Utilities.Serializer;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that controls the Cineplex entity
 * @author Kenneth
 */
public class CineplexController extends Controller {
    /**
     * The list of Cineplexes owned by the company
     */
    private ArrayList<Cineplex> cineplexList;
    
    /**
     * The Cineplex controller singleton
     */
    private static CineplexController instance = null;
    
    /**
     * Creates a new cineplex controller
     */
    protected CineplexController() {
        if (checkStoredListExist("CineplexList")) {
            cineplexList = (ArrayList<Cineplex>) getStoredList("CineplexList");
        }
        else {
            cineplexList = new ArrayList<>();
        }
    }
    
    /**
     * Get the cineplex controller instance
     * @return the cineplex controller
     */
    public static CineplexController getInstance() {
        if(instance==null) {
            instance = new CineplexController();
        }
        return instance;
    }
    
    /**
     * Reset the stored list
     */
    @Override
    public void resetList() {
        cineplexList = new ArrayList<>();
    }
    
    /**
     * Add an empty cineplex to the system
     * @param location the location of the new cineplex
     * @return returns newly created cineplex if successful, null otherwise
     */
    public Cineplex addCineplex(String location) {
        Cineplex tempCineplex;
        if (getCineplexByLocation(location) == null) {
            tempCineplex = new Cineplex(location);
            cineplexList.add(tempCineplex);
            super.updateStoredList(cineplexList,"CineplexList");
            return tempCineplex;
        }
        return null;
    }
    
    /**
     * Get a cineplex by the location name
     * @param location the location of the cineplex
     * @return the Cineplex if cineplex exists, null otherwise
     */
    public Cineplex getCineplexByLocation(String location) {
        for (Cineplex cineplex: cineplexList) {
            if (cineplex.getLocation().equals(location)) {
                return cineplex;
            }
        }
        return null;
    }
    
    /**
     * Remove a Cineplex identified by the location
     * @param location the location of the cineplex to be removed
     * @return returns true if successful, false otherwise
     */
    public boolean removeCineplexByLocation(String location) {
        for (Cineplex cineplex: cineplexList) {
            if (cineplex.getLocation().equals(location)) {
                cineplexList.remove(cineplex);
                super.updateStoredList(cineplexList,"CineplexList");
                return true;
            }
        }
        return false;
    }
    
    /**
     * Create a new cinema (using preset)
     * @param cinemaCode code of the new cinema
     * @param preset preset of the new cinema
     * @return the newly created cinema if successful, null otherwise
     */
    public Cinema createCinema(String cinemaCode, CinemaPreset preset) {
        return new Cinema(cinemaCode,preset);
    }
    
    /**
     * Create a new cinema (using customization)
     * @param cinemaCode code of the new cinema
     * @param width width of the new cinema
     * @param length length of the new cinema
     * @param aisleCount aisle count in the new cinema
     * @param rowComposition row composition of the new cinema
     * @return the newly created cinema if successful, null otherwise
     */
    public Cinema createCinema(String cinemaCode
            ,int width,int length, int aisleCount
            , SeatRowType[] rowComposition) {
        return new Cinema(cinemaCode,width,length,aisleCount,rowComposition);
    }
    
    /**
     * Add a cinema to a cineplex
     * @param cineplex the cineplex to add the Cinema
     * @param cinema the cinema to be added
     */
    public void addCinemaToCineplex(Cineplex cineplex, Cinema cinema) {
         cineplex.addCinema(cinema);
        super.updateStoredList(cineplexList,"CineplexList");
    }
    
    /**
     * Get cinema by code from Cineplex
     * @param cineplex the cineplex to add the Cinema
     * @param cinemaCode the code of the cinema to be retrieved
     * @return the cinema object with the matching cinema code
     */
    public Cinema getCinemaByCode(Cineplex cineplex, String cinemaCode) {
        for (Cinema cinema:cineplex.getCinemas()) {
            if(cinema.getCinemaCode().equals(cinemaCode)) {
                return cinema;
            }
        }
        return null;
    }
    
    /**
     * Get cinema by code from Cineplex
     * @param cinemaCode the code of the cinema to be retrieved
     * @return the cinema object with the matching cinema code
     */
    public Cinema getCinemaByCode(String cinemaCode) {
        for (Cineplex cineplex: cineplexList) {
            for (Cinema cinema:cineplex.getCinemas()) {
                if(cinema.getCinemaCode().equals(cinemaCode)) {
                    return cinema;
                }
            }
        }
        return null;
    }
}
