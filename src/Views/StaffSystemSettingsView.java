package Views;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Controllers.ShowtimeController;
import Models.Cineplex.SeatType;
import Models.Movie.MovieType;
import Models.Showtime.TicketType;
import Models.User.MovieGoerType;

 
/**
 * @author Steve
 *
 */

public class StaffSystemSettingsView {
    /**
     * The staff movie listing view
     */
    private static StaffSystemSettingsView instance = null;
    
    /**
     * Array list of holidays
     */
    protected ArrayList<Date> holidayDates = new ArrayList<>();
    
    /**
     * Array list of ticket type
     */
    private ArrayList<TicketType> ticketTypeList = new ArrayList<>();
    
    /**
     *  Reference to showtime controller
     */
    private ShowtimeController stc;
    
    /**
     *  Create a Staff system setting view instance
     */
    protected StaffSystemSettingsView() {
    	holidayDates = ShowtimeController.getInstance().getHolidays();
    	ticketTypeList = ShowtimeController.getInstance().getAllTicketTypes();
        stc = ShowtimeController.getInstance();
    }
    
    
    /**
     * Get the staff movie listing view instance
     * @return the staff movie listing view instance
     */
    public static StaffSystemSettingsView getInstance() {
    	if (instance == null) {
    		instance = new StaffSystemSettingsView();
    	}
    	return instance;
    }
    
    
    /**
     * Ask for user choice on what changes to make
     */
    public void systemSettingsView(){
    	int choice;
		
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            do {
                System.out.println("\n\n" +
                                "(1) Holidays\r\n" + 
                                "(2) Ticket Prices\r\n" + 
                                "(0) Back");
                System.out.print("Enter the number of your choice:");
                while (!sc.hasNextInt()) sc.next();
                choice = sc.nextInt();
            }while(choice < 0 || choice > 2);

            switch(choice) {
                case 1: changeHolidays();
                    break;
                case 2: changeTicketPrices();
                    break;
                case 0:default:
                    return;
            }
        }
    }
	
    /**
     * Displays selections for Holiday
     */
    public void changeHolidays(){
    	int choice;
        ShowtimeController stc = ShowtimeController.getInstance();
        Scanner sc3 = new Scanner(System.in);
        String input;
		
        Scanner sc2 = new Scanner(System.in);

        while(true) {
             do {
                System.out.println("\n\n" +
                        "(1) View Holidays\r\n" +
                        "(2) Add Holiday\r\n" + 
                        "(3) Remove Holiday\r\n" +
                        "(0) Back");
                System.out.print("Enter the number of your choice:");
                
                while (!sc2.hasNextInt()) sc2.next();
                choice = sc2.nextInt();
                
            }while(choice < 0 || choice > 3);

            switch(choice) {
                case 1:
                    System.out.println("List of holidays:");
                    for (Date holiday: holidayDates) {
                        System.out.println(holiday);
                    }
                    break;
                case 2:
                    System.out.print("Enter date: (DD/MM/YYYY)");
                    input = sc3.nextLine();
                    stc.addHoliday(input);
                    System.out.print("Success");
                    break;
                case 3:
                    System.out.print("Enter date: (DD/MM/YYYY)");
                    input = sc3.nextLine();
                    stc.removeHoliday(input);
                    System.out.print("Success");
                    break;
                case 0:default:
                    return;
            }
            holidayDates = stc.getHolidays();
        }
       
    }

    /**
     * Displays selections for ticket prices options
     */
    public void changeTicketPrices(){
    	int choice;
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        float price;
        
        String input;
        
        while(true) {
            do {
                System.out.println("\n\n" +
                        "(1) View Ticket Prices\r\n" +
                        "(2) Add Ticket Type\r\n" + 
                        "(3) Edit Ticket Prices\r\n" + 
                        "(4) Remove Ticket Type\r\n" +
                        "(0) Back");
                System.out.print("Enter the number of your choice:");

                while (!sc2.hasNextInt()) sc2.next();
                choice = sc2.nextInt();
            }while(choice < 0 || choice > 4);

            switch(choice) {
            case 1:
                    System.out.println("List of ticket prices:");
                    for (TicketType ticketType: ticketTypeList) {
                        System.out.println("================");
                        
                        System.out.println(
                                    "Name: " 
                                        + ticketType.getTicketName());
                        System.out.println(
                                "Seat Type " 
                                        + ticketType.getSeatType());
                        System.out.println(
                                "Holiday Price?" 
                                        + (ticketType.isIsHoliday()?"Yes":"No"));
                        System.out.println(
                                "Customer Type: " 
                                        + ticketType.getMovieGoerType().name());
                        System.out.println(
                                "Movie Type: " 
                                        + ticketType.getMovieType().name());
                        System.out.println(
                                "Price: " 
                                        + ticketType.getPrice());
                    }
                    break;
            case 2:
                addTicket();
                break;
            case 3:
                    System.out.print("Enter ticket type name: ");
                    input = sc3.nextLine();
                    
                    System.out.print("Enter ticket price: ");
                    while (!sc3.hasNextFloat()) sc3.next();
                    price = sc3.nextFloat();
                    stc.editTicketPricing(input, price);
                    sc3.nextLine();
                    break;
            case 4:
                    System.out.print("Enter ticket type name to remove: ");
                    input = sc3.nextLine();
                    
                    stc.removeTicketType(input);
                    break;
            
            case 0:default:
                return;
            }
            ticketTypeList = stc.getAllTicketTypes();
        }
    }
    
    private void addTicket() {
        int choice2;
        
        String ticketName;
        MovieType movieType;
        Boolean isHoliday;
        SeatType seatType;
        MovieGoerType movieGoerType;
        float price;
        
        Scanner sc4 = new Scanner(System.in);
        
        System.out.print("Enter ticket type name: ");
        ticketName = sc4.nextLine();

        System.out.println("Choose movie type:");
        System.out.println("(1) - 2D");
        System.out.println("(2) - 3D");
        do {
            while (!sc4.hasNextInt()) sc4.next();
            choice2 = sc4.nextInt();
        }while(choice2 != 1 && choice2 != 2);

        if(choice2 == 1) {
            movieType = MovieType._2D;
        }
        else {
            movieType = MovieType._3D;
        }

        System.out.println("Is holiday?");
        System.out.println("(1) - Yes");
        System.out.println("(2) - No");

        do {
            while (!sc4.hasNextInt()) sc4.next();
            choice2 = sc4.nextInt();
        }while(choice2 != 1 && choice2 != 2);

        if(choice2 == 1) {
            isHoliday = true;
        }
        else {
            isHoliday = false;
        }

        System.out.println("Choose seat type");
        System.out.println("(1) - Single Seat");
        System.out.println("(2) - Couple Seat");
        System.out.println("(3) - Elite Seat");
        System.out.println("(4) - Ultima Seat");
        System.out.println("(5) - Platinum Suite Seat");

        do {
            while (!sc4.hasNextInt()) sc4.next();
            choice2 = sc4.nextInt();
        }while(choice2 < 1 || choice2 > 5);

        switch(choice2) {
            case 1:
                seatType = SeatType.SINGLE;
                break;
            case 2:
                seatType = SeatType.COUPLE;
                break;
            case 3:
                seatType = SeatType.ELITE;
                break;
            case 4:
                seatType = SeatType.ULTIMA;
                break;
            case 5:
                seatType = SeatType.PLATINUM;
                break;
            default:
                seatType = SeatType.SINGLE;
        }

        System.out.println("Choose movie goer type");
        System.out.println("(1) - Normal");
        System.out.println("(2) - Student");
        System.out.println("(3) - Elderly");

        do {
            while (!sc4.hasNextInt()) sc4.next();
            choice2 = sc4.nextInt();
        }while(choice2 < 1 || choice2 > 5);

        switch(choice2) {
            case 1:
                movieGoerType = MovieGoerType.NORMAL;
                break;
            case 2:
                movieGoerType = MovieGoerType.STUDENT;
                break;
            case 3:
                movieGoerType = MovieGoerType.ELDERLY;
                break;
            default:
                movieGoerType = MovieGoerType.NORMAL;
        }

        System.out.println("Input price of ticket");

        while (!sc4.hasNextFloat()) sc4.next();
        price = sc4.nextFloat();

        if(stc.addTicketType(ticketName
                , movieType
                , isHoliday
                , seatType
                , movieGoerType
                , price) != null) {
            System.out.println("Ticket type succesfully created!");
        }
        else {
            System.out.println("Ticket type not created! Check your inputs!");
        }
                
        
        
    }
    /**
     * Get the list of movies
     * @return array list of movies
     */
    public ArrayList<Date> getHolidayDates(){
    	return holidayDates;
    }
    
    /**
     * Get the list of movies
     * @return array list of movies
     */
    public ArrayList<TicketType> getTicketType(){
    	return ticketTypeList;
    }
 
}