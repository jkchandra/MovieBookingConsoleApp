package Models.Showtime;
import java.util.*;

import Models.Cineplex.*;
import Models.Movie.*;
import Models.User.MovieGoer;
import Utilities.DeepCopy;
import Utilities.Entity;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class representing the showtime of a particular
 * movie in a particular cinema
 * @author Steve
 */
public class Showtime extends Entity {

    private static final long serialVersionUID = 12324134119L;
    
    /**
     * The cinema which shows the movie
     */
    protected Cinema cinema;
	
    /**
     * The movie being shown
     */
    protected Movie movie;
	
    /**
     * Hashtable to be referenced in order to build the booking layout
     */
    protected Hashtable<Integer, SeatRow> referenceLayout;
	
    /**
     * A Hashtable of seat booking layout for each row 
     */
    protected Hashtable<Integer, SeatBooking[]> bookingLayout;
    
    /**
     * List of ticket sold in this showtime
     */
    protected ArrayList<Ticket> ticketSold;
    
    /**
     * Start time of the show time
     */
    private Date startDateTime;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
	
    /**
     * Create a Showtime for a movie in a cinema.
     * @param cinema    cinema where movie is showing
     * @param movie     movie being shown
     * @param startDateTime start date and time of the showtime
     */
    public Showtime(Cinema cinema, Movie movie,Date startDateTime) throws
            IllegalArgumentException{
        try {
            this.referenceLayout 
                = (Hashtable<Integer, SeatRow>) DeepCopy.copy(
                        cinema.getLayout());
        
        }
        catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(
                    "Check cinema if there is layout");
        }
        this.cinema = cinema;
        this.movie = movie;
        this.startDateTime = startDateTime;
        this.bookingLayout = generateLayout();
        this.ticketSold = new ArrayList<>();
    }
    
    /**
     * Create a Showtime for a movie in a cinema.
     * @param cinema    cinema where movie is showing
     * @param movie     movie being shown
     * @param startDateTime start date and time of the showtime
     * @throws java.text.ParseException startDateTime string is incorrect
     */
    public Showtime(Cinema cinema, Movie movie,String startDateTime) throws
            IllegalArgumentException, ParseException{
        try {
            this.referenceLayout 
                = (Hashtable<Integer, SeatRow>) DeepCopy.copy(
                        cinema.getLayout());
        
        }
        catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(
                    "Check cinema if there is layout");
        }
        this.cinema = cinema;
        this.movie = movie;
        this.startDateTime = sdf.parse(startDateTime);
        this.bookingLayout = generateLayout();
        this.ticketSold = new ArrayList<>();
    }
	
    /**
     * Create a seat booking layout, based on the 
     * reference layout.
     * @return layout of seat booking
     */
    private Hashtable<Integer, SeatBooking[]> generateLayout() {
        int i;
        
        Seat tempSeat,tempPairedSeat;
        Hashtable<Integer, SeatBooking[]> tempLayout = new Hashtable<>();
        Set<Integer> keys = referenceLayout.keySet();
        SeatRow seatrow;
        SeatBooking[] seatBooking;

	for (Integer key: keys) {
            seatrow = referenceLayout.get(key);
            seatBooking = new SeatBooking[seatrow.getWidth()];
            i = 0;
            
            for (CinemaCell cell: seatrow.getRow()) {
                if (cell.getClass() == Seat.class) {
                    tempSeat = (Seat) cell;
                    seatBooking[i] = new SeatBooking(
                            tempSeat.getSeatType());
                    if((tempSeat.getPairedSeat()!=null
                            && tempSeat.getPairedSeat().getXCoordinates()<i)){
                        seatBooking[i].setPairSeat(seatBooking[i-1]);
                        seatBooking[i-1].setPairSeat(seatBooking[i]);
                    }
                } 
                else {
                    seatBooking[i] = null;
                }
                i++;
            }
            tempLayout.put(key, seatBooking);
        }
        return tempLayout;
    }
	
    /**
     * Assigns a movie-goer to a particular seat.
     * @param booker    movie-goer to be assigned
     * @param row       row number of seat
     * @param col       column number of seat
     */
    public void bookSeat(MovieGoer booker, int row, int col) {
        this.bookingLayout.get(row)[col].setMovieGoer(booker);
        this.bookingLayout.get(row)[col].getSeatType();

        if (this.bookingLayout.get(row)[col].getPairSeat() != null) {
                this.bookingLayout.get(row)[col].getPairSeat()
                        .setMovieGoer(booker);
        }
    }
	
    /**
     * Removes a movie-goer from a particular seat.
     * @param row     row number of seat
     * @param col     column number of seat
     */
    public void cancelSeat(int row, int col) {
        this.bookingLayout.get(row)[col].removeMovieGoer();

        if (this.bookingLayout.get(row)[col].getPairSeat() != null) {
                this.bookingLayout.get(row)[col].getPairSeat()
                        .removeMovieGoer();
        }
    }
    
    /**
     * Check if seat can be booked; checks if seat exists and if it is available
     * @param row     row number of seat
     * @param col     column number of seat
     * @return SeatBooking object of the chosen seat, null otherwise
     */
    public SeatBooking checkSeat(int row, int col) {
        if (row > bookingLayout.size() && 
                col >= bookingLayout.get(row).length &&
                bookingLayout.get(row)[col].getMovieGoer()!=null) {
            return null;
        }
        else {
            return bookingLayout.get(row)[col];
        }
    }
	
    /**
     * Returns a Hashtable of booking layout.
     * @return Hashtable of booking layout
     */
    public Hashtable<Integer, SeatBooking[]> getBookingLayout() {
        return this.bookingLayout;
    }
    
    /**
     * Prints the booking layout for the showtime
     * @param showtime showtime to print booking layout from
     */
    public static void printBookingLayout(Showtime showtime) {
        SeatBooking[] tempBookingRow;
        Hashtable<Integer,SeatBooking[]> tempLayout 
                = showtime.getBookingLayout();
        Set<Integer> keys = tempLayout.keySet();
        
        for (Integer key: keys) {
            tempBookingRow = tempLayout.get(key);
            for (SeatBooking seatBooking: tempBookingRow) {
                if(seatBooking != null) {
                    System.out.print(seatBooking.getSeatType().toIcon());
                }
                else {
                    System.out.print("   ");
                }
                
            }
            System.out.print("\n");
        }
    }
    
    /**
     * Prints the booking layout provided
     * @param bookingLayout bookingLayout to print
     */
    public static void printBookingLayout(
        Hashtable<Integer,SeatBooking[]> bookingLayout) {
        SeatBooking[] tempBookingRow;
        Hashtable<Integer,SeatBooking[]> tempLayout 
                = bookingLayout;
        Set<Integer> keys = tempLayout.keySet();
        
        for (Integer key: keys) {
            tempBookingRow = tempLayout.get(key);
            for (SeatBooking seatBooking: tempBookingRow) {
                if(seatBooking != null) {
                    System.out.print(seatBooking.getSeatType().toIcon());
                }
                else {
                    System.out.print("   ");
                }
                
            }
            System.out.print("\n");
        }
    }
    
    /**
     * Return the movie showing in this showtime
     * @return movie showing in this showtime
     */
    public Movie getMovie() {
        return movie;
    }
    
    /**
     * Return the cinema used by this showtime
     * @return cinema used
     */
    public Cinema getCinema() {
        return cinema;
    }
    
    /**
     * Return the start date and time of the showtime
     * @return start date and time of the showtime
     */
    public Date getStartDateTime() {
        return startDateTime;
    }

    /**
     * @param cinema the cinema to set
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * @param movie the movie to set
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
