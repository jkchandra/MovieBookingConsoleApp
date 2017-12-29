package Models.Cineplex;

import Utilities.Entity;
import java.util.Hashtable;

/**
 * Class that represents a Cinema in a Cineplex
 * @author Kenneth
 */
public class Cinema extends Entity{
    
    private static final long serialVersionUID = 12324134119L;
    
    /**
     * Seating layout of the cinema
     */
    protected SeatingLayout seatingLayout;
    
    /**
     * Type of cinema; used to generate the preset seating layouts
     */
    protected CinemaPreset presetType;
    
    /**
     * Rows composition of the cinema seating layout
     */
    protected SeatRowType[] rowComposition;
    
    /**
     * Cinema Code of this Cinema
     */
    private String cinemaCode;
    
    /**
     * Width of the Cinema
     */
    private int width;
    
    /**
     * Length of the Cinema
     */
    private int length;
    
    /**
     * Number of aisles in the seating layout
     */
    private int aisleCount;
    
    /**
     * Create a cinema with a customized size and row compositions
     * @param cinemaCode The cinema code of the cinema
     * @param width The width (X) of the cinema
     * @param length The length(Y) of the cinema
     * @param aisleCount The number of 1x1 aisle in the seating layout of the
     *           cinema
     * @param rowComposition the composition of seat rows that will make up the
     *           seating layout of the cinema
     */
    public Cinema(String cinemaCode,int width, int length
            , int aisleCount,SeatRowType[] rowComposition) {
        this.cinemaCode = cinemaCode;
        this.width = width;
        this.length = length;
        this.aisleCount = aisleCount;
        this.presetType = CinemaPreset.CUSTOM;
        this.seatingLayout = new SeatingLayout(width,aisleCount,rowComposition);
        
    }
    
    /**
     * Create a cinema from a preset type with its own fixed dimensions and
     * seating layout
     * @param cinemaCode The cinema code of the cinema
     * @param presetType The cinema preset to generate the seating layout from
     */
    public Cinema(String cinemaCode, CinemaPreset presetType) {
        this.cinemaCode = cinemaCode;
        this.presetType = presetType;
        this.seatingLayout = CinemaPreset.generateLayout(this);
        this.width = seatingLayout.getWidth();
        this.length = seatingLayout.getLength();
        this.aisleCount = seatingLayout.getAisleCount();
    }
    
    /**
     * Print the seating layout of the cinema in the console, for debugging
     * @param cinema the cinema to print the layout from
     */
    public static void printLayout(Cinema cinema) {
        SeatingLayout.printLayout(cinema.seatingLayout);
    }
    
    /**
     * Get the seating layout of the cinema
     * @return The seating layout of the cinema
     */
    public Hashtable<Integer,SeatRow> getLayout() {
        return this.seatingLayout.getLayout();
    }

    /**
     * @return the cinemaCode
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

    /**
     * @param cinemaCode the cinemaCode to set
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }
    
    
}
