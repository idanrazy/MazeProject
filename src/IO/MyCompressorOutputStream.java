package IO;
import java.io.*;

/**
 * Created by linoy on 09/05/2017.
 */
public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;
    int prevB;
    int counter;
    int place;

    public MyCompressorOutputStream(OutputStream OS){
        out=OS;
    }

    public void write(int b) throws IOException {
        if(b==prevB){
            counter++;
        }
        if(b!=prevB){
            byte[] toWrite=new byte[2];
            initilaizeArray(toWrite);
            try {
                out.write(toWrite,0,toWrite.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            counter=1;
            prevB=b;
        }
        if(b==(byte)255){
            byte[] toWrite={(byte)255,1};

            out.write(toWrite,0,toWrite.length);
        }
        /*if(b!=prevB && place!=1){
            byte[] toWrite={(byte)prevB,(byte)counter};
            try {
                out.write(toWrite,0,2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            counter=1;
            prevB=b;
        }*/
    }

    public void write(byte[] arr) throws IOException {
        if(arr.length>0){
            prevB=arr[0];
        }
        counter=1;
        for(place=1;place<arr.length;place++)
        {
            write(arr[place]);
        }
    }

    private void initilaizeArray(byte[] b){
        int length;
        //can write only until 255 byte
        if(counter>254){
            if(counter%255==0){
                length=counter/255;
            }
            else {
                length=(counter/255)+1;
            }
            b=new byte[length*2];
            for(int i=0;i<b.length;i=i+2){
                b[i]=(byte)prevB;
                if(i+1!=b.length-1){
                    b[i+1]=(byte)255;
                }
                else {
                    //write the counter of the last cell that smaller than 255
                    if(counter%255!=0){
                        b[b.length-1]=(byte)(counter-255*((counter/255)+1));
                    }
                    else{
                        b[b.length-1]=(byte)255;
                    }
                }
            }
        }
        //if it's less than 255 byte
        else{
            //first the char
            b[0]=(byte)prevB;
            ///counter
            b[1]=(byte)counter;
        }
    }
}
