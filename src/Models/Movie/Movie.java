package Models.Movie;

import Models.User.MovieGoer;
import Utilities.Entity;
import java.util.*;

/**
 * Class that represents a Movie
 * @author Lim De Quan
 */
public class Movie extends Entity{
    
    private static final long serialVersionUID = 12324134119L;
    
    /**
     * Name of the Movie Title
     */
    private String movieTitle;
    
    /**
     * Duration of the Movie
     */
    private float movieDuration;
    
    /**
     * The screen type of the movie
     */
    private MovieType movieType;
    
    /**
     * Ticket sales of the movie
     */
    private int ticketSales;
    
    /**
     * Array List of the Movie Genre
     */
    private ArrayList<MovieGenre> movieGenre = new ArrayList<>();
    
    /**
     * Rating of the Movie (etc. R, PG...)
     */
    private MovieRating movieRating;
    
    /**
     * Synopsis of the Movie
     */
    private String movieSynopsis;
    
    /**
     * Name of the Movie Director
     */
    private String director;
    
    /**
     * Array List of the Movie Cast
     */
    private ArrayList<String> cast = new ArrayList<>();
    
    /**
     * Showing Status of Move (eg. Coming Soon, Showing Now)
     */
    private ShowingStatus showingStatus;
    
    /**
     * Array of Movie Review Rating
     */
    private ArrayList<Review> reviews = new ArrayList<>();
    
    /**
     * Create a new movie
     * @param movieTitle the title of Movie
     * @param movieDuration the duration of the movie
     * @param movieGenre the list of Genre of the Movie
     * @param movieType the movie type of the movie
     * @param movieRating the rating of the Movie
     * @param movieSynopsis the synopsis of the Movie
     * @param director the director of the Movie
     * @param cast the lists of cast for the Movie
     * @param showingStatus the showing status of the movie
    */
    public Movie(String movieTitle, float movieDuration, 
            ArrayList<MovieGenre> movieGenre,MovieType movieType,
            MovieRating movieRating, String movieSynopsis,String director
            , ArrayList<String> cast, 
            ShowingStatus showingStatus){
        this.movieTitle = movieTitle;
        this.movieDuration = movieDuration;
        this.movieGenre = movieGenre;
        this.movieType = movieType;
        this.movieRating = movieRating;
        this.movieSynopsis = movieSynopsis;
        this.director = director;
        this.cast = cast;
        this.showingStatus = showingStatus;
        this.ticketSales = 0;
    }
    
    /**
     * Create an empty movie
    */
    public Movie() {
        this.movieTitle = null;
        this.movieDuration = 0;
        this.movieGenre = null;
        this.movieType = null;
        this.movieRating = null;
        this.movieSynopsis = null;
        this.director = null;
        this.cast = null;
        this.showingStatus = null;;
        this.ticketSales = 0;
    }
    
    /**
     * Add review to Movie
     * @param reviewer the author of the review
     * @param reviewRating the review rating of the movie
     * @param review the review text of the movie
    */
    public void addReview(MovieGoer reviewer, int reviewRating, String review){
        this.getReviews().add(new Review(reviewer,reviewRating,review));
    }
       
    /**
     * Get the title of the Movie
     * @return title of the Movie
     */
    public String getMovieTitle(){
        return movieTitle;
    }
    
    /**
     * Get the Movie Duration
     * @return Movie Duration
     */
    public float getMovieDuration(){
        return movieDuration;
    }
    
    /**
     * Get the Movie Genres
     * @return Movie Genres
     */
    public ArrayList<MovieGenre> getMovieGenre(){
        return movieGenre;
    }
    
    /**
     * Get the Movie Rating
     * @return Movie Rating
     */
    public MovieRating getMovieRating(){
        return movieRating;
    }
    
    /**
     * Get the Movie Synopsis
     * @return Movie Synopsis
     */
    public String getMovieSynopsis(){
        return movieSynopsis;
    }
    
    /**
     * Get the Movie Director
     * @return Movie Director
     */
    public String getDirector(){
        return director;
    }
    
    /**
     * Get the Movie Casts
     * @return Movie Casts
     */
    public ArrayList<String> getCast(){
        return cast;
    }
    
    /**
     * Get the Movie Showing Status
     * @return Movie Showing Status
     */
    public ShowingStatus getShowingStatus(){
        return showingStatus;
    }
    
    /**
     * Get the Movie Reviews
     * @return list of reviews for the movie
     */
    public ArrayList<Review> getReview(){
        return getReviews();
    }
    
    /**
     * @return the reviews
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }
    
    /**
     * Get the average rating score
     * @return average rating score of the movie
     */
    public float getAvgReview(){
        int i;
        float sum = 0;
        if (getReviews().size() <= 1)
            return -1;
        
        for (i=0; i < getReviews().size(); i++){
            sum += getReviews().get(i).getReviewRating();
        }
        return (float) sum/getReviews().size();
    }

    /**
     * @param movieTitle the movieTitle to set
     */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    /**
     * @param movieDuration the movieDuration to set
     */
    public void setMovieDuration(float movieDuration) {
        this.movieDuration = movieDuration;
    }

    /**
     * @param movieGenre the movieGenre to set
     */
    public void setMovieGenre(ArrayList<MovieGenre> movieGenre) {
        this.movieGenre = movieGenre;
    }

    /**
     * @param movieRating the movieRating to set
     */
    public void setMovieRating(MovieRating movieRating) {
        this.movieRating = movieRating;
    }

    /**
     * @param movieSynopsis the movieSynopsis to set
     */
    public void setMovieSynopsis(String movieSynopsis) {
        this.movieSynopsis = movieSynopsis;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * @param cast the cast to set
     */
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * @param showingStatus the showingStatus to set
     */
    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
    
    /**
     * Adds 1 to ticket sales
     */
    public void addTicketsales() {
        ticketSales++;
    }
    
    /**
     * Sets ticket sales to new value
     * @param newSales value to be set to
     */
    public void setTicketsales(int newSales) {
        ticketSales = newSales;
    }
    
    /**
     * Get the total ticket sales for the movie
     * @return ticket sales for the movie
     */
    public int getSales() {
        return ticketSales;
    }

    /*Comparator for sorting the list by Average Ratings*/
    public static  Comparator<Movie> MovieRatingComparator = new Comparator<Movie>() {

    	 public int compare(Movie m1, Movie m2) {
    	    float MovieRating1 = m1.getAvgReview();
    	    float MovieRating2 = m2.getAvgReview();

    	    return Float.compare(MovieRating2, MovieRating1);
    	 }
    };
   
    /*Comparator for sorting the list by ticket sales*/
    public static Comparator<Movie> MovieSalesComparator = new Comparator<Movie>() {

    	 public int compare(Movie m1, Movie m2) {

    	    int sales1 = m1.getSales();
    	    int sales2 = m2.getSales();

    	    return sales2-sales1;
    	 }
    };
    	 
   
}