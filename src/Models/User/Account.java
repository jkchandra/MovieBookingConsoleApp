package Models.User;

import Utilities.Entity;

/**
 * Class that represents the accounts that can log in to the system
 * @author Chandra
 */

public class Account extends Entity {

    private static final long serialVersionUID = 12324134119L;
    
    /**
     *  User name of Account;
     */
    protected String username;

    /**
     *  Password of Account;
     */
    protected String password;

    /**
     * Create a account with user name and password
     * @param username The user name of the account
     * @param password The password of the account
     */

    public Account(String username, String password) {
             this.username = username;
             this.password = password;
    }

    /**
     * Sets the user name of the account
     * @param username the username to be set to
     */

    public void setUsername(String username) {
            this.username = username;
    }

    /**
     * Sets the password of the account
     * @param password the password to be set to
     */

    public void setPassword(String password) {
            this.password = password;
    }

    /**
     * Gets the user name of the account
     * @return the username of the account
     */

    public String getUsername() {
            return username;
    }

    /**
     * Gets the password of the account
     * @return Password of the account
     */

    public String getPassword() {
            return password;
    }
}
