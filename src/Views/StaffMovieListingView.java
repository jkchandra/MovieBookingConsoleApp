package Views;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;

import Controllers.MovieController;
import Models.Movie.Movie;
import Models.Movie.MovieGenre;
import Models.Movie.ShowingStatus;

 
/**
 * Class that represents 
 * @author Steve
 */
public class StaffMovieListingView {
    /**
     * The staff movie listing view
     */
    private static StaffMovieListingView instance = null;
    
    
    /**
     * Array list of all the movies
     */
    protected ArrayList<Movie> movieList;
    
    /**
     *  
     */
    protected StaffMovieListingView() {
    	this.movieList = MovieController.getInstance().getMovieList();
    }
    
    /**
     * Get the staff movie listing view instance
     * @return the staff movie listing view instance
     */
    public static StaffMovieListingView getInstance() {
    	if (instance == null) {
    		instance = new StaffMovieListingView();
    	}
    	return instance;
    }
    
    /**
     * Ask for user choice at the staff 
     * movie listing view
     */
    public void movieListingView() {
    	int choice;
        Scanner sc = new Scanner(System.in);
        while (true) {
            
            do {
                System.out.println("\n" +
                        "(1) List all Movies\r\n" + 
                        "(2) Add Movie\r\n" + 
                        "(3) Edit Movie\r\n" + 
                        "(4) Remove Movie\r\n" +  
                        "(0) Back");

                System.out.print("Enter the number of your choice:");
                while (!sc.hasNextInt()) sc.next();
                choice = sc.nextInt();
        }while(choice < 0 || choice > 4);
            switch(choice) {
            case 1: listMovies();
                    break;
            case 2: addMovie(sc);
                    break;
            case 3: editMovie(sc);
                    break;
            case 4: removeMovie(sc);
                    break;   
            case 0:default:
                return;
            }
            
        }
    }
		
    /**
     * Prints a list of all the movies
     */
    public void listMovies() {
            int i = 0;
            for (Movie movie: movieList) {
                    System.out.println("==================");
                    System.out.println(i + "." + movie.getMovieTitle());
                    System.out.println("Director: " + movie.getDirector());
                    System.out.println("Sypnosis: " + movie.getMovieSynopsis());
                    System.out.println("Duration: " + movie.getMovieDuration());
                    System.out.println("Status: " + movie.getShowingStatus().name());
                    System.out.println("Genre: ");
                    for(MovieGenre genre: movie.getMovieGenre()) {
                        System.out.println(genre.toString());
                    }
                    System.out.println("Casts: ");
                    for(String cast: movie.getCast()) {
                        System.out.println(cast);
                    }
                    i++;
            }
    }

    /**
     * Create a null movie, fill its value, 
     * and adds it to array list of movies
     */
    public void addMovie(Scanner sc) {
            Movie movie = new Movie();
            sc.nextLine();
            for (int i=1; i<=7; i++) {
                    editMovieField(sc,i, movie);
            }
            movieList.add(movie);
    }

    public void editMovie(Scanner sc) {
            int movieChoice;
            int updateChoice;
            Movie movie;
            Scanner sc2 = sc;
            do {
                listMovies();
                System.out.print("Enter the number of your choice:");
                while (!sc2.hasNextInt()) sc2.next();
                movieChoice = sc2.nextInt();

            } while (movieChoice >= movieList.size());

            movie = movieList.get(movieChoice);

            do {
                    System.out.println("\n\n" +
                            "(1) Movie Title\r\n" + 
                            "(2) Director\r\n" + 
                            "(3) Synopsis\r\n" + 
                            "(4) Duration\r\n" + 
                            "(5) Showing Status\r\n" + 
                            "(6) Genre\r\n" +  
                            "(7) Cast");
                    System.out.print("Enter the field number to edit"
                            + ", type 0 to return to home:");

                    while (!sc2.hasNextInt()) sc2.next();
                    updateChoice = sc2.nextInt();

                    editMovieField(sc2, updateChoice, movie);
            } while (updateChoice < 0 || updateChoice > 7);
    }

    /**
     * Edits a selected part of the movie
     * 
     * @param choice 	Part of the movie to be edited
     * @param movie		Movie to be edited
     */
    void editMovieField(Scanner sc, int updateChoice, Movie movie) {
            String input;
            int choice;
            float duration;
            Scanner sc3 = sc;

            switch (updateChoice) {
            case 1:
                    System.out.println("Enter new movie title: ");
                    input = sc3.nextLine();
                    movie.setMovieTitle(input);
                    System.out.println("Success");
                    break;
            case 2:
                    System.out.println("Enter director name for " 
                            + movie.getMovieTitle() + ".");
                    input = sc3.nextLine();
                    movie.setDirector(input);
                    System.out.println("Success");
                    break;
            case 3:
                    System.out.println("Enter synopsis for " 
                            + movie.getMovieTitle() + ".");
                    input = sc3.nextLine();
                    movie.setMovieSynopsis(input);
                    System.out.println("Success");
                    break;
            case 4:
                    System.out.println("Enter duration for " 
                            + movie.getMovieTitle() + ".");
                    duration = sc3.nextFloat();
                    movie.setMovieDuration(duration);
                    System.out.println("Success");
                    break;
            case 5:
                    System.out.println("Choose showing status: " 
                            + movie.getMovieTitle() + ".");
                    
                    System.out.println("(1) - Coming Soon");
                    System.out.println("(2) - Preview");
                    System.out.println("(3) - Now Showing");
                    System.out.println("(4) - End of Showing");
                    
                    while (!sc3.hasNextInt()) sc3.next();
                    choice = sc3.nextInt();
                    
                    switch(choice) {
                        case 1:
                            movie.setShowingStatus(ShowingStatus.COMINGSOON);
                            break;
                        case 2:
                            movie.setShowingStatus(ShowingStatus.PREVIEW);
                            break;
                        case 3:
                            movie.setShowingStatus(ShowingStatus.NOWSHOWING);
                            break;
                        case 4:
                            movie.setShowingStatus(ShowingStatus.ENDOFSHOWING);
                            break;
                        default:
                            movie.setShowingStatus(ShowingStatus.NOWSHOWING);
                    }
                    System.out.println("Success");
                    sc3.nextLine();
                    break;
            case 6:
                    int count = 1;
                    int select;
                    ArrayList<MovieGenre> movieGenre = new ArrayList<MovieGenre>();
                    System.out.println("Enter genre for " + movie.getMovieTitle() 
                            + ".\nEnter 0 to stop.");
                    
                    ArrayList<MovieGenre> mg = new ArrayList<>();
                    for (MovieGenre x: EnumSet.allOf(MovieGenre.class)) {
                            System.out.println(count + "." + x);
                            count++;
                            mg.add(x);
                    }
                    
                    do {
                            System.out.println("Enter next genre number.");
                            while (!sc3.hasNextInt()) sc3.next();
                            select = sc3.nextInt();
                            if (select == 0) {
                                break;
                            } else if(select > 0 || select < mg.size()) {
                                movieGenre.add(mg.get(select));
                            }
                    } while (select != 0);
                    
                    sc3.nextLine();
                    movie.setMovieGenre(movieGenre);
                    System.out.println("Success");
                    break;
            case 7:
                    ArrayList<String> cast = new ArrayList<>();
                    System.out.println("Enter case for " + movie.getMovieTitle() 
                            + ".\nEnter /end to stop.");
                    do {
                            System.out.print("Enter next cast name.");
                            input = sc3.nextLine();
                            cast.add(input);
                    } while (!"/end".equals(input));

                    movie.setCast(cast);
                    System.out.print("Success");
                    break;
            default:
                    break;
            }
    }
    /**
     * Remove a movie from the array list of movies
     */
    public void removeMovie(Scanner sc) {
            int movieChoice;
            Scanner sc4 = new Scanner(System.in);
            
            
            do {
                    System.out.print("Enter the number of your choice:");
                    movieChoice = sc4.nextInt();

            } while (movieChoice >= movieList.size());

            movieList.remove(movieChoice);
            sc4.close();
    }
	
	
    /**
     * Get the list of movies
     * @return array list of movies
     */
    public ArrayList<Movie> getMovieList(){
    	return movieList;
    }
	

}
