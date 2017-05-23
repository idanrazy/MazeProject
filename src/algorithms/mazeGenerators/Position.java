package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * Create Position rePresented with row value and col value.
 */
public class Position implements Serializable {

   private int RowIndex;
   private int ColumIndex;
   private String data;

    /**
     * Constructor Position
     * @param rowIndex row value
     * @param columnIndex col value
     * @param data String data
     */
    public Position(int rowIndex, int columnIndex, String data) {
        RowIndex = rowIndex;
        ColumIndex = columnIndex;
        this.data = data;
    }

    public int getRowIndex() {
        return RowIndex;
    }

    public int getColumnIndex() {
        return ColumIndex;
    }

    public String getData() {
        return data;
    }

    public void setRowIndex(int rowIndex) {
        RowIndex = rowIndex;
    }

    public void setColumnIndex(int columIndex) {
        ColumIndex = columIndex;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                 RowIndex +
                "," + ColumIndex +
                '}';
    }
}
