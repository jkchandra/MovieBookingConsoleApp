package Models.Movie;

/**
 * Enumeration class of Showing Status
 * @author Lim De Quan, Chandra
 */
public enum ShowingStatus {
	
    COMINGSOON("Coming Soon"),
    PREVIEW("Preview"),
    NOWSHOWING("Now Showing"),
    ENDOFSHOWING("End of Showing");

    /**
     * String describing the showing status
    */
    private final String showStatusDescription;
    
    /**
    * Create a ShowingStatus
    * @param showStatusDescription Description of showing status
    */ 
    ShowingStatus(String showStatusDescription){
	    this.showStatusDescription = showStatusDescription;
	}

    /**
    * Returns the showing status description
    * @return showing status description
    */
	public String toString() {
	    return showStatusDescription;
	}
}
