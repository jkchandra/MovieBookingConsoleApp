package Models.Cineplex;

import java.util.Hashtable;

/**
 * Enumeration class of row types
 * @author Kenneth
 */
public enum SeatRowType {
    SINGLESWHEELCHAIR("Single seats with wheelchair spots at the aisles"),
    COUPLES("Couple seats only"),
    SINGLES("Single seats only"),
    ELITES ("Elite single seats only"),
    ULTIMAS ("Ultima single seats only"),
    PLATINUMS("Platinum suite seats only"),
    BLANKS("Empty row");
    
    /**
     * Name of the row type
     */
    private final String rowTypeName;
    
    /**
     * Create a seat row type with the type name provided
     * @param seatType The seat type used in this type of seat row
     * @param rowTypeName Name of the row type
     */
    private SeatRowType(String rowTypeName){
        this.rowTypeName = rowTypeName;  
    }
    
    /**
     * Identify the seat row type and calls the relevant method to 
     * generate the corresponding row of seats (including empty cells for aisle)
     * @param seatRow the Seat row object containing all the arguments required
     * @return An array of cinema cells representing a row of seats
    */
    public static CinemaCell[] generateLayout(SeatRow seatRow) {
        switch(seatRow.rowType.toString()) {
            case "PLATINUMS":
                return generateSingles(seatRow,SeatType.PLATINUM);
            case "ELITES":
                return generateSingles(seatRow,SeatType.ELITE);
            case "ULTIMAS":
                return generateCouples(seatRow,SeatType.ULTIMA);
            case "SINGLES":
                return generateSingles(seatRow,SeatType.SINGLE);
            case "COUPLES":
                return generateCouples(seatRow,SeatType.COUPLE);
            case "SINGLESWHEELCHAIR":
                return generateSingleWithHandicapped(seatRow,SeatType.SINGLE);
            default:
                return generateBlank(seatRow);
        }
    }
    
    /**
     * Checks if the particular cell provided is potentially an aisle seat
     * @param colNumber X/Column index of the cell
     * @param aisleIndex array of indexes of cell to leave empty for the aisle
     * @return true if cell provided will be an aisle seat, false otherwise
     */
    private static boolean isAisleSeat(int colNumber,int[] aisleIndex) {
        for (int index: aisleIndex) {
            if(colNumber + 1 == index || colNumber - 1 == index ) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the particular cell is in the aisle
     * @param colNumber X/Column index of the cell
     * @param aisleIndex array of indexes of cell to leave empty for the aisle
     * @return true if cell provided in the aisle, false otherwise
     */
    protected static boolean isAisle(int colNumber,int[] aisleIndex) {
        for (int index: aisleIndex) {
            if(colNumber == index) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the particular cell is in the aisle
     * @param colNumbers array of X/Column indexes of the cells
     * @param aisleIndex array of indexes of cells to leave empty for the aisle
     * @return true if cell provided in the aisle, false otherwise
     */
    protected static boolean isAisle(int[] colNumbers,int[] aisleIndex) {
        for (int index: aisleIndex) {
            for (int colNumber: colNumbers) {
                if(colNumber == index) {
                    return true;
                }
            }
        }
        return false;
    }
   
    /**
     * Return the String name of the seat row type
     * @param seatRowType Seat row type object to obtain the type name from
     * @return String name of the seat row type
     */
    public static String getRowTypeName(SeatRowType seatRowType) {
        return seatRowType.rowTypeName;
    }
    
    /**
     * Generates a row of single seaters (including empty cells for aisle)
     * @param seatRow the Seat row object containing all the arguments required
     * @return An array of cinema cells representing a row of seats
     */
    private static CinemaCell[] generateSingles(SeatRow seatRow
            , SeatType seatType) {
        int i;
        int colCtr=0;
        CinemaCell[] tempCells = new CinemaCell[seatRow.rowWidth];
        for(i=0;i<seatRow.rowWidth;i++) {
            if (!(isAisle(i,seatRow.aisleIndexes))) {
                tempCells[i] = new Seat(seatRow.rowNumber,i,
                    seatRow.rowLabel,colCtr++,seatType);
            } 
            else{
                tempCells[i] = new CinemaCell(seatRow.rowNumber,i);
            }
        }
        return tempCells;
    }
    
    /**
     * Generates a row of couple seaters (including empty cells for aisle)
     * @param seatRow the Seat row object containing all the arguments required
     * @return An array of cinema cells representing a row of seats
     */
    private static CinemaCell[]  generateCouples(SeatRow seatRow
            , SeatType seatType) {
        int i;
        int colCtr=0;
        CinemaCell[] tempCells = new CinemaCell[seatRow.rowWidth];
        for(i=0;i<seatRow.rowWidth;i++) {
            // Check if the current cell and next cell are in the aisle
            if ((i+1)<seatRow.rowWidth) {
                if(!(isAisle(new int[]{i,i+1},seatRow.aisleIndexes))) {
                    tempCells[i] = new Seat(i,seatRow.rowNumber,
                        seatRow.rowLabel,colCtr++,seatType);
                    tempCells[i+1] = new Seat(i+1,seatRow.rowNumber,
                        seatRow.rowLabel,colCtr++,seatType);
                    Seat.pairSeat((Seat) tempCells[i],(Seat) tempCells[i+1]);
                    i++;
                }
                else {
                    tempCells[i] = new CinemaCell(seatRow.rowNumber,i);
                }
            } 
            else {
                tempCells[i] = new CinemaCell(seatRow.rowNumber,i);
            }
        }
        return tempCells;
    }
    
    /**
     * Generates a row of single seaters and wheelchair spots along the aisle
     * (including empty cells for aisle)
     * @param seatRow The Seat Row object containing all the arguments required
     * @return An array of cinema cells representing a row of seats
     */
    private static CinemaCell[] generateSingleWithHandicapped(SeatRow seatRow
            , SeatType seatType) {
        int i;
        int colCtr=0;
        CinemaCell[] tempCells = new CinemaCell[seatRow.rowWidth];
        for(i=0;i<seatRow.rowWidth;i++) {
            if (!(isAisle(i,seatRow.aisleIndexes))) {
                // Handicapped seats can only be placed along the aisle
                if(isAisleSeat(i,seatRow.aisleIndexes)) {
                    tempCells[i] = new Seat(seatRow.rowNumber,i,
                        seatRow.rowLabel,colCtr++,SeatType.WHEELCHAIR);
                }
                else {
                    tempCells[i] = new Seat(seatRow.rowNumber,i,
                        seatRow.rowLabel,colCtr++,seatType);
                }
            }
            else {
                tempCells[i] = new CinemaCell(seatRow.rowNumber,i);
            }
        }
        return tempCells;
    }
    
    /**
     * Generates a blank row
     * @param seatRow the Seat row object containing all the arguments required
     * @return An array of cinema cells representing the blank row
     */
    private static CinemaCell[] generateBlank(SeatRow seatRow) {
        int i;
        CinemaCell[] tempCells = new CinemaCell[seatRow.rowWidth];
        for(i=0;i<seatRow.rowWidth;i++) {
            tempCells[i] = new CinemaCell(seatRow.rowNumber,i);
        }
        return tempCells;
    }
}


