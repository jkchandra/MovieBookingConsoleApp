package Controllers;

import Utilities.Deserializer;
import Utilities.Serializer;
import java.io.File;
import java.io.IOException;

/**
 * Superclass to store common methods between the controllers
 * @author Kenneth
 */
public abstract class Controller {
    /**
     * Update the list
     */
    protected final void updateStoredList(Object list, String listName) {
        try {
            Serializer.serialize(list, listName);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieve the list
     */
    protected final Object getStoredList(String listName) {
        try {
            return Deserializer.deserialize(listName);
        }
        catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    
    /**
     * Check if the serialized list exists
     * @param storedListName the name of the serialized file
     * @return true if exist, false otherwise
     */
    protected final boolean checkStoredListExist(String storedListName) {
        File tmpFile = new File("Storage/"+storedListName+".ser");
        return tmpFile.exists() && tmpFile.isFile();
    }
    
    /**
     * Reset the stored list
     */
    public abstract void resetList();
    
}
