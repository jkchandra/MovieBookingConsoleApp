package Utilities;
import java.io.*;


public class Serializer {
    public static void serialize(Object dataObject,String dataName) throws IOException {
        
        FileOutputStream fileOut = new FileOutputStream(
                "Storage/"+dataName+".ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(dataObject);
        out.close();
    }
}
    
	