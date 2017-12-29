package Controllers;


import java.util.ArrayList;
import Models.Movie.*;
import Models.User.MovieGoer;
import java.util.Collections;

/**
 * Class that controls the movie entity
 * @author Lim De Quan, Chandra
 */

public class MovieController extends Controller{
    /**
     * The list of movies shown by the company
     */
    private ArrayList<Movie> movieList;
    
    /**
     * The movie controller singleton
     */
    private static MovieController instance = null;
    
    /**
     * Creates a new movie controller
     */
    protected MovieController() {
        if (checkStoredListExist("MovieList")) {
            movieList = (ArrayList<Movie>) getStoredList("MovieList");
        }
        else {
            movieList = new ArrayList<>();
        }
    }
    
    /**
     * Get the movie controller instance
     * @return the movie controller
     */
    public static MovieController getInstance() {
        if(instance==null) {
            instance = new MovieController();
        }
        return instance;
    }
    
    /**
     * Add new movie information to list
     * @param movieTitle the title of Movie
     * @param movieDuration the duration of the Movie
     * @param movieGenre the list of Genre of the Movie
     * @param movieRating the rating of the Movie
     * @param movieSynopsis the synopsis of the Movie
     * @param director the director of the Movie
     * @param cast the lists of cast for the Movie
     * @param showingStatus the showing status of the movie
     * @return returns the newly created movie if successful, null otherwise
     */
    public Movie addMovie(String movieTitle, float movieDuration, 
            ArrayList<MovieGenre> movieGenre, MovieType movieType,
            MovieRating movieRating, String movieSynopsis,String director
            , ArrayList<String> cast, ShowingStatus showingStatus){
       
        Movie tempMovie = new Movie(movieTitle, movieDuration, movieGenre, movieType
               ,movieRating, movieSynopsis, director, cast, showingStatus);
        
        movieList.add(tempMovie);
        super.updateStoredList(movieList,"MovieList");
        
        return tempMovie;
    }
    
    
    /**
     * Remove the specified Movie from List, identified by movie title
     * @param movieTitle the title of Movie
     * @return return true if success, false otherwise
     */
    public boolean removeMovie (String movieTitle){
        int i;
        for (i=0;i<movieList.size();i++){
            if (movieList.get(i).getMovieTitle().equals(movieTitle)){
                movieList.remove(i);
                super.updateStoredList(movieList,"MovieList");
                return true;
            }
        }
        return false;
    }
    
    /**
     * Return a list of all movie names
     * @return the list of names of all movies
     */
    public ArrayList<String> listAllMovieNames (){
        ArrayList<String> nameList = new ArrayList<>();
        
        movieList.forEach((movie) -> {
            nameList.add(movie.getMovieTitle());
        });
        return nameList;
    }
    
    /**
     * Get movie object of the specified movie
     * @param movieTitle the title of Movie
     * @return the movie with the matching title
     */
    public Movie getMovie (String movieTitle){
        int i;
        for (i=0;i<movieList.size();i++){
            if (movieList.get(i).getMovieTitle().equals(movieTitle)){
               return movieList.get(i);
            }
        }
        return null;
    }
    
    /**
     * Get the Rating and Review of the Specified Movie
     * @param movieTitle the title of Movie
     * @return the list of reviews for the movie
     */
    public ArrayList<Review> getMovieRating (String movieTitle){
        int i;
        for (i=0;i<movieList.size();i++){
            if (movieList.get(i).getMovieTitle().equals(movieTitle)){
                if(movieList.get(i).getReview().isEmpty())
                    return null;
                else{
                    return movieList.get(i).getReview();
                }
            }
        }
        return null;
    }
    
    /**
     * Add Rating and Review for Specified Movie
     * @param movieTitle the title of Movie
     * @param reviewer
     * @param reviewRating
     * @param review of the Movie
     * @return returns true if success, false otherwise
     */
    public boolean addMovieRating (String movieTitle, MovieGoer reviewer, 
            int reviewRating, String review) {
        int i;
        for (i=0;i<movieList.size();i++){
            if (movieList.get(i).getMovieTitle().equals(movieTitle)){
                movieList.get(i).addReview(reviewer, reviewRating, review);
                super.updateStoredList(movieList,"MovieList");
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Comparator to get top five movie rating
     * @return Movie[] A list of top five movies based on movie ratings
     */
    public Movie[] getTopFiveMovieRating (){
        int i;
        
        ArrayList<Movie> MovieList = new ArrayList<>();
        
         for (i=0;i<movieList.size();i++){
            if(movieList.get(i).getAvgReview()!=-1){
            	MovieList.add(movieList.get(i));
            }
        }
        Collections.sort(MovieList, Movie.MovieRatingComparator);
        Movie topFiveMovie[] = new Movie[5];
        if (MovieList.size()>0){
            
            if (MovieList.size() >= 5){
                for (i=0;i<5;i++){
                topFiveMovie[i]=MovieList.get(i);
                }
            }
            else{
                for (i=0;i<MovieList.size();i++){
                topFiveMovie[i]=MovieList.get(i);
                }
            }
            return topFiveMovie;
        }   
        else{
            return null;
        }
    }
    
    /**
     * Comparator method to get top five movies based on ticket sales
     * @return Movie[] A list of top five movies based on ticket sales
     */
    public Movie[] getTopFiveMovieTicket (){
        int i;
        
        ArrayList<Movie> MovieList = new ArrayList<>();
        
         for (i=0;i<movieList.size();i++){
            	MovieList.add(movieList.get(i));
        }

        Collections.sort(MovieList, Movie.MovieSalesComparator);
        Movie topFiveMovie[] = new Movie[5];
        if (MovieList.size()>0){
            
            if (MovieList.size() >= 5){
                for (i=0;i<5;i++){
                topFiveMovie[i]=MovieList.get(i);
                }
            }
            else{
                for (i=0;i<MovieList.size();i++){
                topFiveMovie[i]=MovieList.get(i);
                }
            }
            return topFiveMovie;
        }   
        else{
            return null;
        }
    }
    
    /**
     * Get movie list stored list
     * @return the movie stored list
     */
    public ArrayList<Movie> getMovieList(){
    	return movieList;
    }
    
    /**
     * Get movie list stored list with string
     * @return string of movies
     */
    public ArrayList<String> getAllMovieList(){
    	ArrayList<String> movieTitleList = new ArrayList<>();
        for(Movie movie: movieList) {
            movieTitleList.add(movie.getMovieTitle());
        }
        return movieTitleList;
    }
    
    
    /**
     * Reset the stored list
     */
    @Override
    public void resetList() {
        movieList = new ArrayList<>();
    }
}