package Models.Showtime;
import Models.Cineplex.*;
import Models.User.*;
import Utilities.Entity;

/**
 * Class representing the booking of a seat
 * @author Steve
 */
public class SeatBooking extends Entity{

    private static final long serialVersionUID = 12324134119L;
    
    /**
     * MovieGoer assigned to seat
     */
    protected MovieGoer booker;
	
    /**
     * Type of seat
     */
    protected SeatType seattype;
	
    /**
     * The SeatBooking that this is paired to
     */
    private SeatBooking pairSeat = null;
	
    /**
     * Create a seat and assign it a movie-goer and seat type.
     * @param booker     movie-goer who booked seat
     * @param seattype     type of seat
     */
    public SeatBooking(MovieGoer booker, SeatType seattype) {
        this.booker = booker;
	this.seattype = seattype;
    }
	
    /**
     * Create an empty seat and assign it a seat type.
     * @param seattype type of seat
     */
    public SeatBooking(SeatType seattype) {
	this.booker = null;
        this.seattype = seattype;
    }
	
    /**
     * Returns the seat type of this seat booking.
     * @return SeatType
     */
    public SeatType getSeatType() {
	return this.seattype;
    }
	
    /**
     * Returns the movie-goer who booked this seat.
     * @return MovieGoer
     */
    public MovieGoer getMovieGoer() {
        return this.booker;
    }
	
    /**
     * Assign a seat booking to a movie-goer.
     * @param booker     movie-goer
     */
    public void setMovieGoer(MovieGoer booker) {
	this.booker = booker;
    }
	
    /**
     * Removes movie-goer from assigned seat booking.
     */
    public void removeMovieGoer() {
        this.booker = null;
    }
    
    /**
     * Removes movie-goer from assigned seat booking.
     * @return returns the seatBooking that this is being paired to
     */
    public SeatBooking getPairSeat() {
        return pairSeat;
    }

    /**
     * Set this paired seat to the new pair seat
     * @param pairSeat SeatBooking to pair
     */
    public void setPairSeat(SeatBooking pairSeat) {
        this.pairSeat = pairSeat;
    }
}
