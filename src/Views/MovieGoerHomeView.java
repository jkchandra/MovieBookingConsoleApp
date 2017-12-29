package Views;

import Models.Movie.*;
import Models.User.*;
import java.util.Scanner;
import java.util.ArrayList;
import Controllers.*;

/**
 * View for MovieGoers
 * @author Chandra
 */

public class MovieGoerHomeView {
    /**
     * The home view singleton
     */
    private static MovieGoerHomeView instance = null;
    
    /**
     * The moviegoer object
     */
    private static MovieGoer moviegoer;
	
    /**
     * Creates a new Movie Goer View
     */
    protected MovieGoerHomeView() {
        moviegoer = null;
    }
	
    /**
     * Main View for the Movie Goer (First Run that imports the account 
     * from log in)
     * @param mg MovieGoer object that uses that application
     */
	public void mainView(MovieGoer mg) {
		moviegoer = mg;
		mainView();
	}
        
    /**
     * Get the MovieGoerView instance
     * @return the MovieGoerView instance
     */
    public static  MovieGoerHomeView getInstance() {
        if(instance==null) {
            instance = new MovieGoerHomeView();
        }
        return instance;
    }
	
    /**
     * Main View for the Movie Goer (Main Menu) 
     */
    public void mainView() {
        int choice;
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            do {
                System.out.println("Please input the following choice: ");
                System.out.println("(1) Book");
                System.out.println("(2) Booking History");
                System.out.println("(3) Top 5 Movies");
                System.out.println("(0) Logout");
                while (!sc.hasNextInt()) sc.next();
                choice = sc.nextInt();
            } while (choice < 0 || choice > 3);

            switch(choice) {
                case 1:
                    bookView();
                    break;
                case 2:
                    System.out.println("Go to Booking History");
                    break;
                case 3:
                    topFiveMainView();
                    break;
                case 0:default:
                    return;
            }
        }
        
    }

    /**
     * Book View for the Movie Goer  
     */
    public void bookView() {
        int choice;
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            do {
                System.out.println("How do you want to look for your movies: ");
                System.out.println("(1) List All Movies");
                System.out.println("(2) Search for Movie by Movie Title");
                System.out.println("(0) Back");
                while (!sc.hasNextInt()) sc.next();
                choice = sc.nextInt();
            } while (choice < 0 || choice > 2);

            switch(choice) {
                case 1:
                    listAllMovieDetailsView();
                    break;
                case 2:
                    searchMovieDetailsView();
                    break;
                case 0:default:
                    return;
            }
        }
       
    }
	
    /**
     * View List of Movies for Movie Details
     */
    public static void listAllMovieDetailsView() {
        int choice;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> vMovieList = MovieController.getInstance()
                .getAllMovieList();
        System.out.println("##### List of Movies #####");
        for(int i=0; i < vMovieList.size(); i++) {
                System.out.print("(" + (i+1) + ") ");
                System.out.print(vMovieList.get(i));
                System.out.print("\n");
        } 

        System.out.println("(0) Back");
        do {
                System.out.println("Input a movie number to "
                        + "view more details: ");
                while (!sc.hasNextInt()) sc.next();
                choice = sc.nextInt();
        } while (choice > vMovieList.size() || choice < 0);

        if (choice != 0){
            getInstance().getIndividualMovieDetailsView(
                    vMovieList.get(choice-1));
        }
    }
	
    /**
     * Search for a movie by its movie title
     */
    public static void searchMovieDetailsView() {
        String movieToSearch;
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        ArrayList<Movie> vMovieList = MovieController.getInstance()
                .getMovieList();
        outerloop:
        do {
            System.out.println("Input the movie to be searched, type 0"
                    + " to go back: ");
            movieToSearch = sc.nextLine();

            if(movieToSearch.equals("0")) {
                    return;
            }

            for(int i=0; i < vMovieList.size(); i++) {
                if(vMovieList.get(i).getMovieTitle()
                    .equals(movieToSearch)) {
                    found = true;
                    break outerloop;
                }
            }
            System.out.println("Movie not found!");
        } while (found == false);

        getInstance().getIndividualMovieDetailsView(movieToSearch);
    }
	
    /**
     * View individual movie details and do further actions
     * @param movieTitle The tile of the movie
     */
    public void getIndividualMovieDetailsView(String movieTitle){

        int choice;
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            ArrayList<Movie> vMovieList = MovieController.getInstance()
                    .getMovieList();

            for (int i=0; i<vMovieList.size(); i++){
                if (vMovieList.get(i).getMovieTitle().equals(movieTitle)){
                    System.out.println("Movie Title: " + vMovieList.get(i)
                            .getMovieTitle());
                    if (vMovieList.get(i).getAvgReview() == -1) {
                        System.out.println("Movie Avg Ratings: NA");
                    }
                    else { 
                        System.out.println("Movie Avg Ratings: " 
                                + String.format("%.1f", vMovieList
                                        .get(i).getAvgReview()));
                    }
                    System.out.println("Movie Duration: " 
                            + (int)vMovieList.get(i).getMovieDuration() 
                            + " mins");
                    System.out.println("Movie Genres: " 
                            + vMovieList.get(i).getMovieGenre());
                    System.out.println("Movie Rated: " 
                            + vMovieList.get(i).getMovieRating());
                    System.out.println("Movie Synopsis: " 
                            + vMovieList.get(i).getMovieSynopsis());
                    System.out.println("Movie Director: " 
                            + vMovieList.get(i).getDirector());
                    System.out.println("Movie Casts: " 
                            + vMovieList.get(i).getCast());
                    System.out.println("Movie Showing Status: " 
                            + vMovieList.get(i).getShowingStatus());
                    System.out.println(" ");
                    break;
                }
            }
        
            do {
                System.out.println("What would you like to do: ");
                System.out.println("(1) See Showtime");
                System.out.println("(2) View All Ratings/Review");
                System.out.println("(3) Add Ratings/Review");
                System.out.println("(0) Back to search type");
                while (!sc.hasNextInt()) sc.next();
                choice = sc.nextInt();
            } while(choice < 0 || choice > 3);

            switch(choice) {
                case 1:
                    MovieGoerBookingView.getInstance()
                            .mainView(MovieController.getInstance()
                                    .getMovie(movieTitle));
                    break;
                case 2:
                    seeAllMovieRatingView(movieTitle);
                    break;
                case 3:
                    addMovieRatingView(movieTitle);
                    break;
                case 0:default:
                    return;
            }  
        }
    }

    /**
     * Adds a new movie rating to the movie
     * @param movieTitle Title of the movie to be rated
     */
    public void addMovieRatingView(String movieTitle){

        Scanner sc = new Scanner(System.in);
        int reviewRating;
        
        while (true) {
            System.out.println("Adding " + movieTitle + " Ratings/Review: ");
        
            do {
                System.out.println("Enter Movie Rating: (1-5) or (0) to cancel");
                while (!sc.hasNextInt()) sc.next();
                reviewRating = sc.nextInt(); 
            } while(reviewRating < 0 || reviewRating > 5);  

            if(reviewRating == 0)
                return;	

            System.out.println("Enter Movie Review: ");
            String dummychar = sc.nextLine();
            String review = sc.nextLine();

            boolean added = MovieController.getInstance().addMovieRating(
                movieTitle, moviegoer, reviewRating, review);

            if(added){
                System.out.println(" ");
                System.out.println("**********************");
                System.out.println("Movie Rating Added");
                System.out.println("**********************");
                System.out.println(" ");
                return;
            } 
            else {
                System.out.println("");
                System.out.println("Error in Adding Movie Rating");
            }
        }
        
    }
	
    /**
     * Views all the movie rating of the movie
     * @param movieTitle Title of the movie to be rated
     */
    public void seeAllMovieRatingView(String movieTitle){

        Scanner sc = new Scanner(System.in);
        int choice;
        
        ArrayList<Review> reviewList = MovieController.getInstance()
            .getMovieRating(movieTitle);
        
        if(reviewList == null) {
            System.out.println("No Rating/Reviews Found!");
        }
        else {
            System.out.println("Ratings and Reviews of " 
                + movieTitle + " ########################");

            for (int i = 0; i < reviewList.size(); i++) {
                System.out.println(" ");
                System.out.println("Reviewer: " 
                    + reviewList.get(i).getReviewerName());
                System.out.println("Rating: " 
                    + reviewList.get(i).getReviewRating());
                System.out.println("Review Description: " 
                    + reviewList.get(i).getReview());
            }
        }
        do{
            System.out.println(" ");
            System.out.println("(0) Back");
            while (!sc.hasNextInt()) sc.next();
            choice = sc.nextInt();
        } while(choice != 0);		       
    }
	
    /**
     * Top 5 main view
     */
     public void topFiveMainView(){
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            do {
            System.out.println("Which method do you want to get Top 5 by: ");
            System.out.println("(1) Ticket Sales");
            System.out.println("(2) Average Ratings");
            System.out.println("(0) Back");
            while (!sc.hasNextInt()) sc.next();
            choice = sc.nextInt();
            } while(choice < 0 || choice > 2);

            switch(choice) {
                case 1:
                    getInstance().topFiveTicketView();
                    break;
                case 2:
                    getInstance().topFiveRatingsView();
                    break;
                case 0:default:
                    return;
            }
        }
    }
	 
	 
    /**
     * Get the top 5 movies based on ticket sales
     */
    public void topFiveTicketView(){
        Scanner sc = new Scanner(System.in);
        int choice;

        Movie topFiveMovie[] = new Movie[5] ;
        
        while (true) {
            if(MovieController.getInstance().getTopFiveMovieTicket()==null) {
                System.out.println("There are no Movies.");
                return;
            } 
            else{
                topFiveMovie = MovieController.getInstance().getTopFiveMovieTicket();
                for(int i=0; i<5;i++) {
                    System.out.println("("+ (i+1) + ") " 
                            + topFiveMovie[i].getMovieTitle() 
                            + " Ratings: "+ topFiveMovie[i].getSales());
                }
            }
            
            System.out.println("(0) Back");
            
            do {
                System.out.println("Input the number of the movie to be searched");
                while (!sc.hasNextInt()) sc.next();
                choice = sc.nextInt();
            } while(choice < 0 || choice > 5);

            if (choice == 0 ) {
                return;
            }
            else {
                getInstance().getIndividualMovieDetailsView(topFiveMovie[choice-1]
                        .getMovieTitle());
            }
        }
        
    }
	 
    /**
     * Get the top 5 movies based on average ratings
     */
    public void topFiveRatingsView(){
        Scanner sc = new Scanner(System.in);
        int choice;
        Movie topFiveMovie[] = new Movie[5] ;

        while(true) {
            if(MovieController.getInstance().getTopFiveMovieRating()==null) {
                System.out.println("There are no Movies.");
                return;
            }
            else{
                topFiveMovie = MovieController.getInstance().getTopFiveMovieRating();
                for(int i=0; i<5;i++) {
                    if (topFiveMovie[i] == null) {
                            break;
                    }
                    else {
                            System.out.println("("+ (i+1) + ") "
                                    + topFiveMovie[i].getMovieTitle() 
                                    + " Ratings: "+ topFiveMovie[i].getAvgReview());	
                    }    
                }
                System.out.println("(0) Back");
            }

            do {
                System.out.println("Input the number of the movie to be searched: ");
                while (!sc.hasNextInt()) sc.next();
                choice = sc.nextInt();
            } while(choice < 0 || choice > 5);

            if (choice == 0 ) {
                return;
            }
            else {
                getInstance().getIndividualMovieDetailsView(
                         topFiveMovie[choice-1].getMovieTitle());
            }
        }
        
    }
}
