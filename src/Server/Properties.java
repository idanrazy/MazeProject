package Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by idanr on 29/05/2017.
 */
public class Properties {

    public java.util.Properties prop = new java.util.Properties();
    OutputStream output = null;
    public  Properties(){
        try {
            output = new FileOutputStream("config.properties");
            prop.setProperty("GenerateAlg","MyMazeGenerator");
            prop.setProperty("SearchAlg","BestFirstSearch");
            prop.setProperty("MaxThread","10");
            prop.store(output,null);


        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(output!=null)
                try{
                output.close();
                }
                catch (Exception e){
                e.printStackTrace();
                }
        }
    }

}
