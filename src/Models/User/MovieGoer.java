package Models.User;

import Models.Showtime.SeatBooking;
import Models.Showtime.Showtime;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Class that represents MovieGoer account
 * @author Lim De Quan, Chandra
 */

public class MovieGoer extends Account{
    
    private static final long serialVersionUID = 12324134119L;
    
    /**
     * Type of the MovieGoer
     */
    private MovieGoerType type;
    
    /**
     * Name of the MovieGoer
     */
    private String name;
    
    /**
     * MobileNumber of the MovieGoer
     */
    private int mobileNumber;
    
    /**
     * Email of the MovieGoer
     */
    private String email;
    
    /**
     * The booking history of the user
     */
    private Hashtable<Showtime,ArrayList<SeatBooking>> bookingHistory;
    
    /**
     * Create a MovieGoer account with a user name and password
     * @param username The user name of the MovieGoer
     * @param password The password of the MovieGoer
     * @param type The type of the MovieGoer
     * @param name The name of the MovieGoer
     * @param mobileNumber The mobile number of the MovieGoer
     * @param email The email of the MovieGoer
     */
    public MovieGoer (String username,String password, MovieGoerType type
            ,String name, int mobileNumber, String email){
        super(username, password);
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.type = type;
        this.bookingHistory = new Hashtable<>();
    }
    
    /**'
     * Sets age of MovieGoer
     * @param type type of the MovieGoer
     */
    public void setType(MovieGoerType type) {
    	this.type = type;
    }
    
    /**
     * Gets age of MovieGoer
     * @return the type of this MovieGoer
     */
    public MovieGoerType getType() {
    	return type;
    }
    
    /**
     * Gets name of MovieGoer
     * @return the name of the MovieGoer
     */
    public String getName() {
        return name;
    }
    
    /**
     * Add booking history to user
     */
    public void addBookingHistory(Showtime showtime
            , SeatBooking seatbooking) {
    	ArrayList<SeatBooking> tempList;
        
        if(bookingHistory.containsKey(showtime)) {
            bookingHistory.get(showtime).add(seatbooking);
        }
        else {
            tempList = new ArrayList<>();
            tempList.add(seatbooking);
            bookingHistory.put(showtime, tempList);
        }
    }
}

