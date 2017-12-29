package Models.Movie;

/**
 * Enumeration class of Movie Genre
 * @author Lim De Quan, Chandra
 */
public enum MovieGenre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HISTORICAL("Historical"),
    HORROR("Horror"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCI_FI("Sci Fi"),
    THRILLER("Thriller"),
    ANIMATION("Animation");
    
    /**
     * String of Movie Genre
     */
    private final String movieGenreName;
    
    /**
     *Create a Movie Genre
     * @param movieRatingDescription the description of movie rating
     */
    MovieGenre(String movieGenreName){
        this.movieGenreName = movieGenreName;
    }
    
    /**
     * Returns the movie genre name
     * @return the movie genre name
     */
    public String toString() {
        return movieGenreName;      
    }
    
}