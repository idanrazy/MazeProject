package test;

import java.io.*;

/**
 * Created by idanr on 15/05/2017.
 */
public class check {
    public static void main(String[] args) {
        byte[] b = new byte[100];
        byte[] b2 = new byte[100];
        try {
            InputStream in = new ByteArrayInputStream(b);
            OutputStream out = new ByteArrayOutputStream();
            ObjectInputStream a = new ObjectInputStream(in);
            ObjectOutputStream c = new ObjectOutputStream(out);

            out.write(b);
            out.flush();


            InputStream in2 = new ByteArrayInputStream(b2);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}




