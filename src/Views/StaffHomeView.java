package Views;

import Models.User.*;
import java.util.Scanner;

/**
 * View for staff to navigate admin options
 * @author Soggeh
 */
public class StaffHomeView {
    /**
     * The staff home view
     */
    private static StaffHomeView instance = null;
    
    /**
     * The logged in staff
     */
    private Staff validatedStaff;
    
    protected StaffHomeView() {
        validatedStaff = null; 
    }
    
    /**
     * Get the staff Home View instance
     * @return the staff Home View instance
     */
    public static StaffHomeView getInstance() {
        if(instance==null) {
            instance = new StaffHomeView();
        }
        return instance;
    }
    
    public void homeView(Staff validatedStaff) {
        this.validatedStaff = validatedStaff;
        Scanner sc = new Scanner(System.in);
        int choice;
        
        while (true) {
            System.out.println("Select your option:"
                + "\n(1) - Create/Modify Movie Listing"
                + "\n(2) - Create/Modify Cinema Showtimes"
                + "\n(3) - Configure System Settings"
                + "\n(0) - Log out");
            
            while (!sc.hasNextInt()) sc.next();
            choice = sc.nextInt();
            
            switch(choice) {
                case 1:
                    StaffMovieListingView.getInstance().movieListingView();
                    break;
                case 2:
                    StaffShowtimeView.getInstance().showtimeView();
                    break;
                case 3:
                    StaffSystemSettingsView.getInstance().systemSettingsView();
                    break;
                case 0:default:
                    return;
            }
        }
    }
}
