package Models.User;

/**
 * Class that represents Staff account
 * @author Lim De Quan, Chandra
 */

public class Staff extends Account {
    
    private static final long serialVersionUID = 12324134119L;
    
    /**
     * Create a Staff account with a user name and password
     * @param username The user name of the person
     * @param password The password of the person
     */

    public Staff(String username, String password) {
            super(username, password);
    }

}
