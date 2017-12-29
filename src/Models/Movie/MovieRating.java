package Models.Movie;

/**
 * Enumeration class of Movie Rating
 * @author Lim De Quan, Chandra
 */
public enum MovieRating {
    G("General"),
    PG("Parental Guidance Suggested"),
    PG_13("Parents Strongly Cautioned"),
    R("Restricted"),
    NC_17("Adults Only");
    
    /**
     * String description describing the movie rating
     */
    private final String movieRatingDescription;
	
    /**
     * Create a Movie Rating
     * @param movieRatingDescription Description of movie rating
    */ 
    MovieRating(String movieRatingDescription){
	this.movieRatingDescription = movieRatingDescription;
    }

    /**
     * Returns the movie rating description
     * @return movie rating description
     */
    public String toString() {
        return movieRatingDescription;
    }
}    



