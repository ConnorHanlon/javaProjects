import com.sun.org.apache.xpath.internal.SourceTree;
import java.io.*;
import java.io.FileReader;
/*
Part IV
A)
Memory Usage for SparseIntMatrix implementation:
If only the memory used in storing the matrix elements is considered for memory
storage, if there are n number of matrix entries, then the amount of memory is
m*5. One unit is assigned to the row label, one unit is assigned to the column
label, one unit is assigned to the data, one assigned to the next row entry, and
one assigned to the next column entry. Therefore, there are  units allocated per
matrix entry, and with m matrix entries, the total amount of memory is m*5.

Memory Usage for standard 2D array:
With an N x N array, If each entry consists of only 1 memory unit,
 the total amount of memory is n*n.

B)
More space-efficient, and if so, by how much?For what value of m does the 2D array become more space-efficient than
the SparseIntMatrix implementation?

The space used in the 2D array would be roughly 100,000*1,000,000 which is
100 trillion. The space used for the sparse int matrix depends on the amount
of matrix entries entered. In order for the 2D matrix to be more space efficient,
the number of matrix entries would have to be 20 trillion.
(100 trillion  = 5 * m ; m = 20 trillion.) So as long as the number of
matrix entries is less than 20 trillion, the sparse int matrix is more space
efficient.
 */
public class SparseIntMatrix {
    private int rows, cols;
    private MatrixEntry[] colMH, rowMH;

    public SparseIntMatrix(int numRows, int numCols) {
        rows = numRows;
        cols = numCols;
        colMH = new MatrixEntry[cols];
        rowMH = new MatrixEntry[rows];
    }


    /**
     * Constructor, which instantiates two lists that keep track of the heads of each linked list.
     * @param numRows number of rows in the sparse matrix
     * @param numCols number of columns in the sparse matrix
     * @param inputFile file containing the data entries used to populate the sparse matrix.
     */
    public SparseIntMatrix(int numRows, int numCols, String inputFile) {
        rows = numRows+1;
        cols = numCols+1;
        colMH = new MatrixEntry[cols];
        rowMH = new MatrixEntry[rows];
        try {
            File f = new File(inputFile);
            BufferedReader bRead = new BufferedReader(new FileReader(f));
            String s;
            int[] x = new int[3];
            while ((s = bRead.readLine()) != null) {
                String[] stringArr = s.split("\\,");
                for (int i = 0; i < stringArr.length; i++) {
                    x[i] = Integer.parseInt(stringArr[i]);
                }
                MatrixEntry entry = new MatrixEntry(x[0], x[1], x[2]);
                addToColArray(rowMH, entry);
                addToRowArray(colMH, entry);
            }
        } catch (java.io.IOException e) {
            System.out.println("File not found");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Adds a matrix entry to the end of the column linked list.
     * @param meList the list of the heads of the linked row lists.
     * @param me the entry to be added to the meList.
     */
    public void addToColArray(MatrixEntry[] meList, MatrixEntry me ) {
        if (meList[me.getColumn()] == null) {
            meList[me.getColumn()] = me;
        }else {
            MatrixEntry headerEntryAtColumn = meList[me.getColumn()];
            MatrixEntry endOfList = getEndOfColumnList(headerEntryAtColumn);
            endOfList.setNextColumn(me);
        }
    }

    /**
     * Adds a matrix entry to the end of the row linked list.
     * @param meList the list of the heads of linked row lists.
     * @param me the entry to be added to the meList.
     */
    public void addToRowArray(MatrixEntry[] meList, MatrixEntry me ){
        if (meList[me.getRow()] == null) {
            meList[me.getRow()] = me;
        }else {
            MatrixEntry endOfList = getEndOfRowList(meList[me.getRow()]);
            endOfList.setNextRow(me);
        }
    }

    /**
     * Searches for an element first at the designated spot in the column header matrix, and
     * if a matrix entry exists, the linked list is iterated through until the row is either
     * found or not found. If found, the element returns its data. Otherwise, 0 is returned.
     * @param r row of desired element.
     * @param c column of desired element.
     * @return the data associated to the desired matrix entry if one exists, otherwise returns zero.
     */

    public int getElement(int r, int c) {
        MatrixEntry currentRow = colMH[r];
        if (currentRow != null) {
            return findData(currentRow, r, c);
        }
        return 0;
    }

    /**
     * Searches through a linked list to find the desired matrix entry, and returns the data associated.
     * Assists the getElement method.
     * @param me the head matrix entry that is linked to other matrix entries.
     * @param r desired row
     * @param c desired column
     * @return data associated to the desired matrix entry.
     */
    public int findData(MatrixEntry me, int r, int c) {
        while (me != null) {
            if (me.getColumn() == c && me.getRow() == r) {
                return me.getData();
            }
                me = me.getNextRow();
        }
        return 0;
    }

    /**
     * Finds the last matrix entry of the row linked list.
     * @param currentMatrixEntry the head of the matrix entry row.
     * @return last matrix entry of the row list.
     */
    public MatrixEntry getEndOfRowList(MatrixEntry currentMatrixEntry) {
        while (currentMatrixEntry != null) {
            if(currentMatrixEntry.getNextRow()!= null) {
                currentMatrixEntry = currentMatrixEntry.getNextRow();
            }else{
                return currentMatrixEntry;
            }
        }
        return currentMatrixEntry;
    }

    /**
     * Finds the last matrix entry of the column linked  list.
     * @param currentMatrixEntry head of the matrix entry column.
     * @return last matrix entry of the column list.
     */
    public MatrixEntry getEndOfColumnList(MatrixEntry currentMatrixEntry) {
        while (currentMatrixEntry != null) {
            if(currentMatrixEntry.getNextCol()!= null) {
                currentMatrixEntry = currentMatrixEntry.getNextCol();
            }else{
                return currentMatrixEntry;
            }
        }
        return currentMatrixEntry;
    }


    public int getNumCols() {
        return cols;
    }

    public int getNumRows() {
        return rows;
    }


    /**
     * Adds another matrix to an existing matrix. If the addition ends up with zero data, the matrix entry is removed
     * from the existing sparse matrix.
     * @param otherMat the matrix to be added to an existing matrix.
     * @return the existing sparse matrix after adding the other matrix.
     */
    public boolean plus(SparseIntMatrix otherMat) {
        for(int i = 0; i<rowMH.length; i++) {
            System.out.println("i= " + i);
            for(int j = 0; j<colMH.length; j++) {
                if (this.getElement(i, j) == 0 && otherMat.getElement(i, j) == 0) {
                    continue;
                    //both cases null, so just move on to next iteration
                } else if (this.getElement(i, j) == 0) {
                    int addData = otherMat.getElement(i, j);
                    MatrixEntry newEntry = new MatrixEntry(i, j, addData);
                    addToColArray(rowMH, newEntry);
                    addToRowArray(colMH, newEntry);
                    this.setMatrixElement(i, j, otherMat.getElement(i, j));
                } else if (otherMat.getElement(i, j) == 0) {
                    continue;
                } else if (this.getElement(i, j) != 0 && otherMat.getElement(i, j) != 0) {
                    int addData = this.getElement(i, j) + otherMat.getElement(i, j);
                    this.setMatrixElement(i, j, addData);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Helper method for the plus and minus methods. This method sets the data associated to a specific matrix element.
     * If the data of the matrix entry is zero, that matrix entry is removed from the sparse matrix.
     * @param row row index of the desired matrix entry.
     * @param cols column index of the desired matrix entry.
     * @param data the data to be associated to the desired matrix entry.
     */
    public void setMatrixElement(int row, int cols, int data) {
        MatrixEntry current = colMH[row];
        MatrixEntry previous = current;
        while (current.getColumn() != cols && current.getNextRow() != null) {
            previous = current;
            current = current.getNextRow();
        }
        if (data == 0) {
            previous.setNextRow(current.getNextRow());
            current = null;
        } else {
            current.setData(data);
        }
    }

    /**
     * Subtracts another sparse matrix from an existing one.
     * @param otherMat the other matrix which is subtracted from the existing matrix.
     * @return the existing sparse matrix after subtracting the other matrix.
     */
    public boolean minus(SparseIntMatrix otherMat) {
        for(int i = 0; i<rowMH.length; i++) {
            for(int j = 0; j<colMH.length; j++) {
                if (this.getElement(i, j) == 0 && otherMat.getElement(i, j) == 0) {
                    continue;
                } else if (this.getElement(i, j) == 0) {
                    int subData = -(otherMat.getElement(i, j));
                    MatrixEntry newEntry = new MatrixEntry(i, j, subData);
                    addToColArray(rowMH, newEntry);
                    addToRowArray(colMH, newEntry);
                } else if (otherMat.getElement(i, j) == 0) {
                    continue;
                } else if (this.getElement(i, j) != 0 && otherMat.getElement(i, j) != 0) {
                    int subData = getElement(i, j) - otherMat.getElement(i, j);
                    setMatrixElement(i, j, subData);
                } else {
                   return false;
                }
            }
        }
        return true;
    }
}
