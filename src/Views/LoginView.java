package Views;

import Controllers.AccountController;
import Models.User.*;
import java.util.Scanner;

/**
 * View for user to log in
 * @author Soggeh
 */
public class LoginView {
    /**
     * The login singleton
     */
    private static LoginView instance = null;
    
    /**
     * The username inputted
     */
    private String usernameInput;
    
    /**
     * The password inputted
     */
    private String passwordInput;
    
    /**
     * The account object of the validated user
     */
    private Account validatedAccount;
    
    /**
     * Creates a new Login View
     */
    protected LoginView() {
        usernameInput = null;
        passwordInput = null;
    }
    
    public void login() {
        Scanner sc = new Scanner(System.in);
            
        while(true) {
            System.out.println("Please enter your username"
                    + ", type quit to exit application:");
            usernameInput = sc.nextLine();
            
            if ("quit".equals(usernameInput)) {
                return;
            }
            
            System.out.println("Please enter your password:");
            passwordInput = sc.nextLine();
        
            validatedAccount = AccountController.getInstance().getUser(
                usernameInput);

            
            
            if (validatedAccount != null 
                && validatedAccount.getPassword().equals(passwordInput) ) {
                
                if (validatedAccount.getClass() == Staff.class) {
                    StaffHomeView.getInstance().homeView(
                            (Staff) validatedAccount);
                }
                else {
                   MovieGoerHomeView.getInstance().mainView(
                           (MovieGoer) validatedAccount);
                }
            } else {
                System.out.println("ERROR: Wrong Credentials! "
                        + "Please try again.");
            }
        }
    }
    
    /**
     * Get the Login View instance
     * @return the Login View instance
     */
    public static  LoginView getInstance() {
        if(instance==null) {
            instance = new LoginView();
        }
        return instance;
    }
}
