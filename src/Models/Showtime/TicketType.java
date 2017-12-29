package Models.Showtime;
import Models.Movie.MovieType;
import Models.Cineplex.SeatType;
import Models.User.MovieGoerType;
import Utilities.Entity;

/**
 * Class that represents the Ticket Type
 * @author Lim De Quan
 */

public class TicketType extends Entity{
    
    private static final long serialVersionUID = 12324134119L;
    
    /**
     *  Name of Ticket Type;
     */
    private String ticketName;
    
    /**
     *  Name of Movie Type;
     */
    private MovieType movieType;
    
    /**
     *  Boolean of whether is it Holiday or Weekend;
     */
    private boolean isHoliday;
    
    /**
     *  Name of SeatType;
     */
    private SeatType seatType;
    
    /**
     *  Name of SeatType;
     */
    private MovieGoerType movieGoerType;
    
    /**
     * Price of Ticket Type;
     */
    private float price;
    
    /**
     * Create a TicketType
     * @param ticketName The name of ticket
     * @param movieType The movie type of ticket
     * @param isHoliday Whether it is Public Holiday or Weekend Ticket
     * @param seatType The seat type of ticket (eg. Couple seat, platinum..)
     * @param movieGoerType The Movie Goer Type (eg. Elderly, Student..)
     * @param price The price of ticket
     */
    public TicketType(String ticketName, MovieType movieType, boolean isHoliday
            , SeatType seatType,MovieGoerType movieGoerType, float price) {
        this.ticketName = ticketName;
        this.movieType = movieType;
        this.isHoliday = isHoliday;
        this.seatType = seatType;
        this.movieGoerType = movieGoerType;
        this.price = price;
    }

    
    /**
     * Returns the price of movie ticket
     * @return price of movie ticket
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return the ticketName
     */
    public String getTicketName() {
        return ticketName;
    }

    /**
     * @param ticketName the ticketName to set
     */
    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    /**
     * @return the movieType
     */
    public MovieType getMovieType() {
        return movieType;
    }

    /**
     * @param movieType the movieType to set
     */
    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    /**
     * @return the isHoliday
     */
    public boolean isIsHoliday() {
        return isHoliday;
    }

    /**
     * @param isHoliday the isHoliday to set
     */
    public void setIsHoliday(boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    /**
     * @return the SeatType
     */
    public String getSeatType() {
        return seatType.toString();
    }

    /**
     * @param SeatType the SeatType to set
     */
    public void setSeatType(SeatType SeatType) {
        this.seatType = SeatType;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the movieGoerType
     */
    public MovieGoerType getMovieGoerType() {
        return movieGoerType;
    }

    /**
     * @param movieGoerType the movieGoerType to set
     */
    public void setMovieGoerType(MovieGoerType movieGoerType) {
        this.movieGoerType = movieGoerType;
    }
}
