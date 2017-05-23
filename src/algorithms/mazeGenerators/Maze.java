package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Create Maze object
 */
public class Maze implements Serializable {
    private int Rows;
    private int Cols;
    private Position start_point;
    private Position end_point;
    private int Maze[][];

    /**
     * Maze constructor
     * @param rows Number of rows in the Maze
     * @param cols Number of columns in the Maze
     * @param start_point Position of the start Point
     * @param end_point Position of the End Point
     * @param Maze Integer matrix with 0 to represent Path or 1 to represent Wall
     */
    public Maze(int rows, int cols, Position start_point, Position end_point, int[][] Maze) {
        Rows = rows;
        Cols = cols;
        this.start_point = start_point;
        this.end_point = end_point;
        this.Maze = Maze;
    }

    public Maze(byte[] b) {

        Byte i1=new Byte((byte) (b[0] & (0xFF)));
        Rows=i1.intValue();
        Byte i2=new Byte((byte)(b[1] &(0xff)));
        Cols=i2.intValue();
        //insert maze value
        int newNum1=b[0];
        int newNum2=b[1];
        if(b[0]<0){
            newNum1=b[0]+256;
            Rows=newNum1;
        }
        if(b[1]<0){
            newNum2=b[1]+256;
            Cols=newNum2;
        }
        int[][] temp=new int[newNum1][newNum2];
        int m1=2;
        for(int i=0;i<newNum1;i++){
            for(int j=0;j<newNum2;j++){
                Byte m=new Byte((byte)(b[m1] &(0xff)));
                temp[i][j]=m.intValue();
                m1++;
            }
        }
        setMaze(temp);
        //insert start position
        //Byte i3=new Byte((byte)(b[m1] & (0xff)));
        //Byte i4=new Byte((byte)(b[m1+1] & (0xff)));
        newNum1=b[m1];
        newNum2=b[m1+1];
        if(b[m1]<0)
            newNum1=b[m1]+256;
        if(b[m1+1]<0){
            newNum2=b[m1+1]+256;
        }
        //String s1=String.valueOf(i3.intValue())+","+String.valueOf(i4.intValue());
        String s1=String.valueOf(newNum1+","+newNum2);
        //start_point=new Position(i3.intValue(),i4.intValue(),s1);
        start_point=new Position(newNum1,newNum2,s1);
        //insert out position
        //Byte i5=new Byte((byte)(b[m1+2] &(0xff)));
        //Byte i6=new Byte((byte)(b[m1+3] & (0xff)));
        newNum1=b[m1+2];
        newNum2=b[m1+3];
        if(b[m1+2]<0)
            newNum1=b[m1+2]+256;
        if(b[m1+3]<0){
            newNum2=b[m1+3]+256;
        }
        //String s2=String.valueOf(i5.intValue())+","+String.valueOf(i6.intValue());
        String s2=String.valueOf(newNum1+","+newNum2);
        end_point=new Position(newNum1,newNum2,s2);
        //end_point=new Position(i5.intValue(),i6.intValue(),s2);
    }

    public int getRows() {
        return Rows;
    }

    public void setRows(int rows) {
        Rows = rows;
    }

    public int getCols() {
        return Cols;
    }

    public void setCols(int cols) {
        Cols = cols;
    }

    /**
     *
     * @return  the start Position
     */
    public Position getStartPosition() {
        return start_point;
    }

    /**
     *
     * @param  start_point the start point
     */
    public void setStartPosition(Position start_point) {
        this.start_point = start_point;
    }

    /**
     *
     * @return  the goal point
     */
    public Position getGoalPosition() {
        return end_point;
    }

    public void setGoalPosition(Position end_point) {
        this.end_point = end_point;
    }

    /**
     *
     * @return Integer matrix with 0 to rePresent Path or 1  to represent Wall
     */
    public int[][] getMaze() {
        return Maze;
    }

    /**
     *
     * @param Maze Integer Maze
     */
    public void setMaze(int[][] Maze) {
        this.Maze = Maze;
    }

    /**
     * Print the Maze
     */
    public void print(){
        for(int i=0;i<Rows;i++)
        {
            for(int j=0;j<Cols;j++)
            {
                if(i==start_point.getRowIndex() && j==start_point.getColumnIndex())
                    System.out.print("S"+" ");
                else if(i==end_point.getRowIndex() && j==end_point.getColumnIndex())
                    System.out.print("E"+" ");
                else if(Maze[i][j]==1)
                    System.out.print("\u2588"+" ");
                else
                    System.out.print("\u2591"+" ");
            }
            System.out.println("");

        }
    }
    public byte[] toByteArray() {
        ArrayList<Byte> ans = new ArrayList();
        //insert number of row and column at the maze
        ans.add((byte)(Rows &(0xFF)));
        ans.add((byte)(Cols &(0xFF)));
        //adding the maze
        ArrayList<Byte> maze=convertMaze();
        ans.addAll(maze);
        //adding start position
        ans.add ((byte)(start_point.getRowIndex() & (0xFF)));
        ans.add((byte)(start_point.getColumnIndex() &(0xFF)));
        //adding end position
        ans.add((byte)(end_point.getRowIndex() &(0xFF)));
        ans.add((byte)(end_point.getColumnIndex()&(0xFF)));
        int length=ans.size();
        byte[] byteAns=new byte[length+1];
        for(int i=0;i<byteAns.length-1;i++){
            byteAns[i]= ans.get(i);
        }
        //insert char to mark the end of file
        byteAns[byteAns.length-1]=(byte)(255 &(0xFF));
        return byteAns;
    }

    public ArrayList<Byte> convertMaze() {
        ArrayList<Byte> ans = new ArrayList<>();
        byte b;
        //throw ll rows
        for (int i = 0; i < getRows(); i++) {
            {
                //throw the col
                for (int j = 0; j < getCols(); j++) {
                    b=(byte)(Maze[i][j]&(0xFF));
                    ans.add(b);
                }
            }
        }
        return ans;
    }

    @Override
    public String toString() {
        String s ="";
        for(int i=0;i<Maze.length;i++){
            for(int j=0;j<Maze[i].length;j++){
                s=s+Maze[i][j];
            }
        }
        return  s;
    }
}
