package Controllers;

import java.util.ArrayList;
import Models.User.*;

/**
 * Class that Control the Accounts
 * @author Chandra
 */

public class AccountController extends Controller{
    private ArrayList<Account> accountList;
    
    /**
     * The movie controller singleton
     */
    private static AccountController instance = null;
    
    /**
     * Creates a new movie controller
     */
    protected AccountController() {
        if (checkStoredListExist("AccountList")) {
            accountList = (ArrayList<Account>) getStoredList("AccountList");
        }
        else {
            accountList = new ArrayList<>();
        }
    }
    
    /**
     * Reset the stored list
     */
    @Override
    public void resetList() {
        accountList = new ArrayList<>();
    }
    
    
    /**
     * Get the movie controller instance
     * @return the movie controller
     */
    public static AccountController getInstance() {
        if(instance==null) {
            instance = new AccountController();
        }
        return instance;
    }
    
    /**
     * Add new MovieGoer to system
     * @param username username of movie goer
     * @param password password of movie goer
     * @param type the type of movie goer
     * @param name name of the movie goer
     * @param mobileNumber mobile number of the movie goer
     * @param email email of the movie goer
     */
    public void addMovieGoer(String username, String password,
            MovieGoerType type, String name, int mobileNumber, String email){
       accountList.add(new MovieGoer(username, password, 
               type, name, mobileNumber, email));
       super.updateStoredList(accountList,"AccountList");
    }
    
    /**
     * Add new staff to the system
     * @param username Username of staff
     * @param password Password of staff
     */
    public void addStaff(String username, String password){
       accountList.add(new Staff(username, password));
       super.updateStoredList(accountList,"AccountList");
    }
    
    /**
     * Remove account from system
     * @param username The username of Movie
     * @return returns true if success, false otherwise
     */
    public boolean removeAccount(String username){
        int i;
        for (i=0;i<accountList.size();i++){
            if (accountList.get(i).getUsername().equals(username)){
            	accountList.remove(i);
                super.updateStoredList(accountList,"AccountList");
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get Account Object
     * @param username the name of the user
     * @return the account object
     */
    public Account getUser(String username){
        int i;
        for (i=0;i<accountList.size();i++){
            if (accountList.get(i).getUsername().equals(username)){
            	return accountList.get(i);
            }
        }
        return null;
    }

}
    
    