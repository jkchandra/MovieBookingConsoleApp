package Utilities;
import java.io.*;

public class Deserializer {
    public static Object deserialize(String dataObjectName) throws IOException
            , ClassNotFoundException{
            FileInputStream fileIn = 
                new FileInputStream("Storage/" 
                        + dataObjectName + ".ser");
            
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object readDataObject = in.readObject();
            
            in.close();
            fileIn.close();
            
            return readDataObject;
    }
}