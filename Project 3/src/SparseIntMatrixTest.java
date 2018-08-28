
public class SparseIntMatrixTest{

    public static void main(String[] args) {

        //Test One.
        SparseIntMatrix mat = new SparseIntMatrix(1000, 1000, "C:\\Users\\Connor\\Desktop\\CSCI\\CSCI 1933 Intro to Alg and Data Structures\\Projects\\Project 3\\src\\matrix1_data.txt");
        MatrixViewer.show(mat);

        //Test Two.
        SparseIntMatrix matrix2_data = new SparseIntMatrix(1000, 1000, "C:\\Users\\Connor\\Desktop\\CSCI\\CSCI 1933 Intro to Alg and Data Structures\\Projects\\Project 3 Files from Teacher\\matrix2_data.txt");
        SparseIntMatrix matrix2_noise = new SparseIntMatrix(1000, 1000, "C:\\Users\\Connor\\Desktop\\CSCI\\CSCI 1933 Intro to Alg and Data Structures\\Projects\\Project 3 Files from Teacher\\matrix2_noise.txt");
        matrix2_data.minus(matrix2_noise);
        MatrixViewer.show(matrix2_data);
    }

}


