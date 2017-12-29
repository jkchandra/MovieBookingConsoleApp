package Views;

import Controllers.ShowtimeController;
import Models.Cineplex.SeatRow;
import Models.Movie.*;
import Models.Showtime.SeatBooking;
import Models.Showtime.Showtime;

import Models.User.MovieGoer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

/**
 * Class that represents the Booking view for movie goers
 * @author Kenneth
 */
public class MovieGoerBookingView {
    /**
     * The Booking View Singleton
     */
    private static MovieGoerBookingView instance = null;
    
    /**
     * Showtime controller, essential for booking purposes
     */
    private static ShowtimeController showtimeController;
    
    
    /**
     * The moviegoer object
     */
    private static MovieGoer moviegoer;
    
    /**
     * The movie object
     */
    private static Movie viewedMovie;
    
    /**
     * Sets date format as dd/M/yyyy
     */
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
    /**
     * The list of showtimes for the currently viewed movie;
     */
    private static ArrayList<Showtime> viewedShowtimeList;
    
    /**
     * Creates a new Movie Goer View
     */
    protected MovieGoerBookingView() {
        moviegoer = null;
        showtimeController = ShowtimeController.getInstance();
    }
    
    /**
     * Get the MovieGoerBookingView instance
     * @return the MovieGoerBookingView instance
     */
    public static MovieGoerBookingView getInstance() {
        if(instance==null) {
            instance = new MovieGoerBookingView();
        }
        return instance;
    }
    
    public void mainView(Movie viewedMovie) {
        int i, choice;
        Scanner sc = new Scanner(System.in);
        viewedShowtimeList = showtimeController
                .getShowtimeListForMovie(viewedMovie);
        
        if(viewedShowtimeList == null) {
            System.out.println("No showtimes for such movie!");
            return;
        }
        
        i = 0;
        while(true) {
             do {
            for (Showtime showtime: viewedShowtimeList) {
            System.out.println("("+ i+1 +") " 
                    + showtime.getCinema().getCinemaCode() 
                    + ", "
                    + sdf.format(showtime.getStartDateTime()));
            System.out.println("(0) Back");
            }
            while (!sc.hasNextInt()) sc.next();
            choice = sc.nextInt();
            
            }while (choice < 0 || choice > i );

            if (choice == 0) {
                return;
            }
            
            seatingLayoutBooking(viewedShowtimeList.get(i-1));
        }
    }
    
    public void seatingLayoutBooking(Showtime showtime) {
        Hashtable<Integer,SeatBooking[]> tempLayout 
                = showtime.getBookingLayout();
        SeatBooking[] tempBookingRow;
        Set<Integer> keys = tempLayout.keySet();
        int cinemaSize = tempLayout.get(0).length;
        int i;
        
        System.out.print(" ");
        for (i=0;i<cinemaSize;i++) {
            System.out.print(i);
        }
        
        for (Integer key: keys) {
            tempBookingRow = tempLayout.get(key);
            System.out.print(SeatRow.intToRowLabel(key)+" ");
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
    
}
