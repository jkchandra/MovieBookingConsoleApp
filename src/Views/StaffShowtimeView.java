package Views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

import Controllers.CineplexController;
import Controllers.ShowtimeController;
import Models.Cineplex.Cinema;
import Models.Movie.Movie;
import Models.Movie.MovieGenre;
import Models.Movie.ShowingStatus;
import Models.Showtime.Showtime;

/**
 * View for staff to add showtime
 * @author Steve dawg
 */
public class StaffShowtimeView {
    /**
     * The staff show time view
     */
    private static StaffShowtimeView instance = null;
    
    /**
     * The Hashtable of Movie and Showtime
     */
    protected Hashtable<Movie,ArrayList<Showtime>> showtimeList;
    
    /**
     * Get the staff show time instance
     * @return the staff show time instance
     */
    public static StaffShowtimeView getInstance() {
        if(instance==null) {
            instance = new StaffShowtimeView();
        }
        return instance;
    }
    
    /**
     * Ask for user choice at the staff 
     * show time view
     */
    public void showtimeView() {
        showtimeList = ShowtimeController.getInstance().getShowtimeList();
    	int choice;

        Scanner sc = new Scanner(System.in);

        do {
                System.out.println("\n\n" +
                                "(1) View all Existing Show Times\r\n" +
                                "(2) Add Show Time for Existing Movie\r\n" + 
                                "(3) Add Show Time for New Movie\r\n" + 
                                "(4) Remove Show Time\r\n" + 
                                "(0) Back");
                System.out.print("Enter the number of your choice:");
                while (!sc.hasNextInt()) sc.next();

        choice = sc.nextInt();
        switch(choice) {
            case 1: viewShowtime();
                    break;

            case 2: addShowtimeExistingMovie();
                    break;

            case 3: addShowtimeNewMovie();    		
                    break;

            case 4: removeShowtime();
                    break;

            case 0:default: return;
            }			
            }while(choice < 0 || choice >3);
            System.out.println("You have exited. Thank you");
            sc.close();
    }
		
    /**
     * Prints all the movies with their show times and cinema
     */
    public void viewShowtime() {
        int i = 0;
        Set<Movie> movies = showtimeList.keySet();
        for (Movie movie: movies) {
                System.out.println((i+1) + ". Movie title: " + movie.getMovieTitle());
                System.out.println("Showtimes: ");
                for (Showtime showtime: showtimeList.get(movie)) {
                        System.out.println("\t" + showtime.getStartDateTime() + showtime.getCinema().getCinemaCode());
                }
                i++;
        }
        // Somehow get back to StaffMovieListingView
    }
	
    /**
     * Add show times to an existing movie at a given cinema
     */
    public void addShowtimeExistingMovie() {
        int i = 0, j = 0;
        int choice;
        String input;
        Cinema cinema;
        Movie[] movies = (Movie[]) showtimeList.keySet().toArray();
        Scanner sc2 = new Scanner(System.in);
        for (Movie movie: movies) {
                System.out.println((i+1) + ". Movie title: " + movie.getMovieTitle());
                i++;
        }
        do{
                System.out.print("Enter the number of your choice:");
                choice = sc2.nextInt()-1;
        } while (choice > movies.length);

        Movie movie = movies[choice];

        ArrayList<Showtime> movieShowtime = showtimeList.get(movie);

        System.out.println("Showtimes: ");
        for (Showtime showtime: movieShowtime) {
                System.out.println((j+1) + ". " + showtime.getStartDateTime() + " at " + showtime.getCinema().getCinemaCode());
                j++;
        }
        do {
                System.out.print("Enter the cinema code: ");
                input = sc2.nextLine();
                cinema = CineplexController.getInstance().getCinemaByCode(input);
        } while (cinema != null);

        do {
                System.out.print("Enter the show time (/end to stop): (dd/M/yyyy HH:mm:ss)");
                input = sc2.nextLine();
                ShowtimeController.getInstance().addShowtime(cinema, movie, input);
        } while (input != "/end");

        showtimeList = ShowtimeController.getInstance().getShowtimeList();
        sc2.close();
		
    }
	
    /**
     * Add a new movie and it's show times at a given cinema
     */
    public void addShowtimeNewMovie() {
        String input;
        Cinema cinema;
        Scanner sc2 = new Scanner(System.in);
        Movie movie = null;
        Showtime showtime;
        for (int i=1; i<=6; i++) {
                StaffMovieListingView.getInstance().editMovieField(sc2, i, movie);
        }

        do {
                System.out.print("Enter the cinema code: ");
                input = sc2.nextLine();
                cinema = CineplexController.getInstance().getCinemaByCode(input);
        } while (cinema != null);

        do {
                System.out.print("Enter the show time (/end to stop): (dd/M/yyyy HH:mm:ss)");
                input = sc2.nextLine();
                ShowtimeController.getInstance().addShowtime(cinema, movie, input);
        } while (input != "/end");


        showtimeList = ShowtimeController.getInstance().getShowtimeList();
        sc2.close();
    }


    /**
     * Remove a show time of a movie in a cinema
     */
    public void removeShowtime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
        int i = 0, j = 0;
        int choice;
        Showtime showtime;
        Movie[] movies = (Movie[]) showtimeList.keySet().toArray();
        Scanner sc2 = new Scanner(System.in);
        for (Movie movie: movies) {
                System.out.println((i+1) + ". Movie title: " + movie.getMovieTitle());
                i++;
        }
        do{
                System.out.print("Enter the number of your choice:");
                choice = sc2.nextInt()-1;
        } while (choice > movies.length);

        Movie movie = movies[choice];

        ArrayList<Showtime> movieShowtime = showtimeList.get(movie);

        System.out.println("Showtimes: ");
        for (Showtime show: movieShowtime) {
                System.out.println((j+1) + ". " + show.getStartDateTime() + " at " + show.getCinema().getCinemaCode());
                j++;
        }
        do {
                System.out.print("Enter the number of your choice: ");
                choice = sc2.nextInt()-1;
        } while (choice > movieShowtime.size());

        showtime = movieShowtime.get(choice);


        ShowtimeController.getInstance().removeShowtime(showtime.getCinema(), movie, sdf.format(movieShowtime.get(choice).getStartDateTime()));

        showtimeList = ShowtimeController.getInstance().getShowtimeList();
        sc2.close();
    }
	
	
    /**
     * Get the hashtable of movies and their showtimes
     * @return the hashtable of movies and their showtimes
     */
    public Hashtable<Movie, ArrayList<Showtime>> getShowtimeList() {
        return showtimeList;
    }
}
