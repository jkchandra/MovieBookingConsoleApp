package Models.Cineplex;

/**
 * Enumeration class of Cinema types
 * @author Kenneth
 */
public enum CinemaPreset {
    PLATINUM("A small cinema with only Platinum suite seats"),
    LARGE("Large Cinema with a good mix of the variety of seats"),
    MEDIUM("Medium Cinema with only normal single and couple seats"),
    SMALL("Small Cinema with only normal single seats"),
    CUSTOM("A Cinema with a auto-generated layout");
    
    /**
     * Descriptor for the cinema preset
     */
    private String cinemaTypeDescriptor;
    
    /**
     * Create a Cinema type with the type provided
     * @param cinemaTypeDescriptor Name of the cinema preset
     */
    private CinemaPreset(String cinemaTypeDescriptor){
        this.cinemaTypeDescriptor = cinemaTypeDescriptor;  
    }
    
    
    
    /**
     * Identify the Cinema presets and generate the corresponding
     * seating layout
     * @param cinema The cinema object containing all arguments required to
     * generate the layout
     * @return A preset seating layout object
     */
    public static SeatingLayout generateLayout(Cinema cinema) {
        SeatRowType[] tempComposition;
        switch(cinema.presetType.toString()) {
            case "PLATINUM":
                tempComposition = new SeatRowType[]{
                    SeatRowType.PLATINUMS,
                    SeatRowType.PLATINUMS,
                    SeatRowType.PLATINUMS,
                    SeatRowType.PLATINUMS,
                    SeatRowType.PLATINUMS,
                    SeatRowType.PLATINUMS
                };
                return new SeatingLayout(9,1,tempComposition);
            
            case "LARGE":
                tempComposition = new SeatRowType[]{
                    SeatRowType.ULTIMAS,
                    SeatRowType.ELITES,
                    SeatRowType.ELITES,
                    SeatRowType.BLANKS,
                    SeatRowType.COUPLES,
                    SeatRowType.COUPLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES
                };
                return new SeatingLayout(35,2,tempComposition);
                
            case "MEDIUM":
                tempComposition = new SeatRowType[]{
                    SeatRowType.COUPLES,
                    SeatRowType.COUPLES,
                    SeatRowType.COUPLES,
                    SeatRowType.BLANKS,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES
                };
                return new SeatingLayout(20,1,tempComposition);
                
            case "SMALL":
                tempComposition = new SeatRowType[]{
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES
                };
                return new SeatingLayout(11,1,tempComposition);
            default:
                 tempComposition = new SeatRowType[]{
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES,
                    SeatRowType.SINGLES
                };
                return new SeatingLayout(11,1,tempComposition);
        }
    }
}
