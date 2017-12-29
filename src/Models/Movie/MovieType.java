package Models.Movie;

/**
 * Enumeration Type of Movie
 * @author Lim De Quan
 */
public enum MovieType {
    _3D("3D Movie"),
    _2D("2D Movie");
    
    /**
     * String Description Describing the Movie Type
     */
    private final String movieTypeDescription;
	
    /**
     * Create a Movie Type
     * @param movieTypeDescription Description of movie type
    */ 
    MovieType(String movieTypeDescription){
	this.movieTypeDescription = movieTypeDescription;
    }

    /**
     * Returns the movie rating description
     * @return movie rating description
     */
    public String toString() {
        return movieTypeDescription;
    }
}    
