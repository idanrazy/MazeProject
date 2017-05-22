package IO;

import java.io.*;

/**
 * Created by linoy on 09/05/2017.
 */
public class MyDecompressorInputStream extends InputStream {
    InputStream in;
    int counter;
    int prevB;

    public MyDecompressorInputStream(InputStream IN){
        in=IN;
        counter=0;
    }

    @Override
    public int read() throws IOException {
        byte[] fromFile = new byte[1];
        in.read(fromFile,counter,1);
        counter++;
        return fromFile[0];
    }

    public int read(byte[] b) throws IOException {
        byte[] fromFile = new byte[1];
        int c=0;
        int l = in.available();
        for (int i = 0; c!=b.length&&in.available()>0; i = i + 2) {
            int b1=in.read() & 0xff;
            prevB=b1;
            if(b1==(byte)(255 &0xff))
                break;
            int b2 = in.read() &0xff;
            for (int j = 0; j < b2; j++) {
                b[c] = (byte)b1 ;
                c++;
            }

        }
        int a;
        return c;
    }
}
