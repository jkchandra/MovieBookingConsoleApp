package Models.User;

/**
 * Enumeration class of MovieGoer Type
 * @author Chandra
 */

public enum MovieGoerType {
	NORMAL("Normal"),
	STUDENT("Student"),
	ELDERLY("Elderly");
	
    /**
     * String of MovieGoer Type
     */
    private final String movieGoerTypeDescription;

    /**
     *Create a MovieGoer Type
     * @param movieGoerTypeDescription Description of MovieGoer Type
     */
    MovieGoerType(String movieGoerTypeDescription){
   	this.movieGoerTypeDescription = movieGoerTypeDescription;
    }

    /**
     * Returns the MovieGoer Type description
     * @return movie genre name
     */
    public String toString() {
   	return movieGoerTypeDescription;      
    }    
}
