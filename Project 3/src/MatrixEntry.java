public class MatrixEntry {
    private int r,c,d;
    private MatrixEntry nextColEntry;
    private MatrixEntry nextRowEntry;

    public MatrixEntry() {
        r = 500;
        c = 500;
        d = 1;
    }



    public MatrixEntry(int row, int col, int data) {
        r = row;
        c = col;
        d = data;
    }

    public int getColumn() {
        return c;
    }

    public void setColumn(int col) {
        c = col;
    }

    public int getRow() {
        return r;
    }

    public void setRow(int row) {
        r = row;
    }

    public int getData() {
        return d;
    }

    public void setData(int data) {
        d = data;
    }

    public void setNextColumn(MatrixEntry el) {
        nextColEntry = el;
    }

    public MatrixEntry getNextCol() {
        return nextColEntry;
    }

    public void setNextRow(MatrixEntry el) {
        nextRowEntry = el;
    }

    public MatrixEntry getNextRow() {
        return nextRowEntry;
    }
}
