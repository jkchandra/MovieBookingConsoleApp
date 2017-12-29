package Models.Cineplex;

import java.util.Hashtable;
import java.io.Serializable;
import java.util.Set;

/**
 * Class that represents the seating layout for a cinema
 * @author Kenneth
 */
public class SeatingLayout implements Serializable{
    
    private static final long serialVersionUID = 12324134119L;
    
    /**
     * Width of the layout; corresponds to the width of the Cinema
     */
    private int layoutWidth;
    
    /**
     * Number of aisles in the layout
     */
    private int aisleCount;
    
    /**
     * Row composition of the layout;rows start from the back (i.e. the entrance)
     */
    private SeatRowType[] rowsComposition;
    
    /**
     * The seating layout of the cinema stored as a hash table
     */
    private Hashtable<Integer,SeatRow> seatingLayout;
    
    /**
     * Create a seating layout based on the width of the Cinema and the
     * ordered composition of the seat rows
     * @param layoutWidth the width of the Cinema
     * @param rowsComposition the composition of seat row types stored as an array
     * Rows starting from the entrance begins as the first element of the array
     */
    public SeatingLayout(int layoutWidth, int aisleCount, SeatRowType[] rowsComposition ) {
        this.layoutWidth = layoutWidth;
        this.rowsComposition = rowsComposition;
        this.aisleCount = aisleCount;
        seatingLayout = generateLayout(this);
    }
    
    /**
     * Get the width (X) of the seating layout
     * @return Width of the seating layout
     */
    public int getWidth() {
        return this.layoutWidth;
    }
    
    /**
     * Get the length (Y) of the seating layout
     * @return Length of the seating layout
     */
    public int getLength() {
        return seatingLayout.size();
    }
    
    /**
     * Get the number of aisles in the seating layout
     * @return Number of aisles in the seating layout
     */
    public int getAisleCount() {
        return aisleCount;
    }
    
    /**
     * Generate the layout for the cinema based on the parameters provided
     * @return layout of the cinema, stored in a Hashtable
     */
    private static Hashtable<Integer,SeatRow> generateLayout(SeatingLayout layout) {
        Hashtable<Integer,SeatRow> newLayout = new Hashtable<>();
        int[] aisleIndexes = generateAisleIndex(layout.layoutWidth,
                layout.aisleCount);
        int rowCtr = 1;
        for (SeatRowType rowType: layout.rowsComposition) {
            newLayout.put(rowCtr++,new SeatRow(rowCtr,layout.layoutWidth,
                    aisleIndexes,rowType));
        }
        return newLayout;
    }
    
    /**
     * Generate an array of indexes of cells to leave empty for the aisles
     * @param layoutWidth The width of the layout
     * @param aisleCount The number of aisle to include in the layout
     * @return an array of indexes of cells specified for the aisle
     */
    private static int[] generateAisleIndex(int layoutWidth, int aisleCount) {
        int i;
        int[] aisleIndex;
        int multiplier = layoutWidth/(aisleCount+1);
        
        aisleIndex = new int[aisleCount];
        for (i=0;i<aisleCount;i++) {
            aisleIndex[i]=multiplier*(i+1);
        }
        return aisleIndex;
    }
    
    /**
     * Gets the number of each seat type in the seating layout
     * @param seatingLayout the seating layout to count the number of each seat type
     * @return A Hashtable of count for each seat type in the seating layout
     */
    public static Hashtable<String,Integer> getCapacityByType(
            SeatingLayout seatingLayout) {
        int i;
        Hashtable<String,Integer> layoutCapacityByType = new Hashtable<>();
        Hashtable<String,Integer> tempRowCapacityByType;
        Hashtable<Integer,SeatRow> tempLayout = seatingLayout.getLayout();
        Set<String> keys2;
        
        Set<Integer> keys = tempLayout.keySet();
        for (Integer key: keys) {
            tempRowCapacityByType = 
                    SeatRow.countSeatsByType(tempLayout.get(key));
            
            keys2 = tempRowCapacityByType.keySet();
            for (String key2: keys2) {
                i = layoutCapacityByType.get(key2);
                if (layoutCapacityByType.containsKey(key2)) {
                    layoutCapacityByType.put(key2,++i);
                }
                else {
                    layoutCapacityByType.put(key2,i);;
                }
            }    
        }
        return layoutCapacityByType;
    }
    
    /**
     * Gets the total number of seats in the seating layout
     * @param seatingLayout The seating layout to count the total number of seats
     * @return Total number of seats
     */
    public static int getCapacity(SeatingLayout seatingLayout) {
        int tempRowCapacity;
        Hashtable<Integer,SeatRow> tempLayout = seatingLayout.getLayout();
        Set<Integer> keys = tempLayout.keySet();
        int layoutCapacity = 0;
        
        for (Integer key: keys) {
            tempRowCapacity = 
                    SeatRow.countTotalSeats(tempLayout.get(key));
            layoutCapacity += tempRowCapacity;
        }
        return layoutCapacity;
    }
    
    /**
     * Get the seating layout, stored in a Hashtable
     * @param seatingLayout the layout object to obtain the seating layout
     * @return seating layout
     */
    public Hashtable<Integer,SeatRow> getLayout() {
        return this.seatingLayout;
    }
    
    /**
     * Print the seating layout, stored in a Hashtable, for debugging purposes.
     * Prints the rows starting from the screen
     * @param seatingLayout the layout object with the seating layout to be printed
     */
    public static void printLayout(SeatingLayout seatingLayout) {
        CinemaCell[] tempSeatRow;
        Seat tempSeat;
        String tempIcon;
        Hashtable<Integer,SeatRow> tempLayout = seatingLayout.getLayout();
        Set<Integer> keys = tempLayout.keySet();
        
        for (Integer key: keys) {
            tempSeatRow = tempLayout.get(key).cells;
            for (CinemaCell cell: tempSeatRow) {
                if(cell.getClass()== Seat.class) {
                    tempSeat = (Seat) cell;
                    tempIcon = tempSeat.getSeatType().toIcon();
                    System.out.print(tempIcon);
                }
                else {
                    System.out.print("   ");
                }
                
            }
            System.out.print("\n");
        }
    }
    
    /**
     * Get the seating layout, stored in a Hashtable
     * @param newLayout the new layout to replace
     * 
     */
    public void setLayout(Hashtable<Integer,SeatRow> newLayout) {
        this.seatingLayout = newLayout;
    }
}
