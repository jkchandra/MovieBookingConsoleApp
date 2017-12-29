package Models.Cineplex;

import Utilities.Entity;

/**
 * Class that represents an empty cell in the seating layout of a cinema
 * @author Kenneth
 */
public class CinemaCell extends Entity {
    
    private static final long serialVersionUID = 12324134119L;
    
    /**
     * X Coordinates of the cell in the seating layout
     */
    protected int xCoordinates;
    
    /**
     * Y Coordinates of the cell in the seating layout
     */
    protected int yCoordinates;
    
    /**
     * Create an empty cell in the layout at the X and Y coordinates provided
     * @param x The X coordinates of the cell
     * @param y The Y coordinates of the cell
     */
    public CinemaCell(int x, int y){
        this.xCoordinates = x;
        this.yCoordinates = y;
    }
    
    /**
     * Get the X coordinates of the cell
     * @return X coordinates of the cell
     */
    public int getXCoordinates() {
        return this.xCoordinates;
    }
    
    /**
     * Get the Y coordinates of the cell
     * @return Y coordinates of the cell
     */
    public int getYCoordinates() {
        return this.yCoordinates;
    }
    
    /**
     * Change X coordinate of of cell
     * @param x X coordinates to change to
     */
    public void setX(int x) {
        this.xCoordinates = x;
    }
    
    /**
     * Change Y coordinate of of cells
     * @param y Y coordinates to change to
     */
    public void setY(int y) {
        this.yCoordinates = y;
    }
    
}

