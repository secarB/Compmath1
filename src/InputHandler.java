import java.io.*;

public class InputHandler {
    private int range;
    private double[][] a;
    private double[] b;
    private double erf;
    private Matrix matrix;
    private BufferedReader fromKeyboard = new BufferedReader(new InputStreamReader(System.in));
    private String type;
    public InputHandler() throws IOException
    {
        validateInputType();
        if (type.equals("1")) {
            getMatrixFromKeyBoard();
        }
        else { getMatrixFromFile();}
    }

    public void getMatrixFromKeyBoard() throws IOException {
        validateArrayRange();
        a = new double[range][range];
        b = new double[range];
        validateMatrix();
        validateErrorFunction();
        matrix = new Matrix(range, a, b, erf);
    }

    private void validateInputType() throws IOException {
        readInputType();
    }

    private void readInputType() throws IOException{
        System.out.println("Enter type of input 1: from console 2: from file");
        type = fromKeyboard.readLine();
        while (!((type.equals("1")) || (type.equals("2"))))  {
            System.err.println("input must be 1 or 2");
        }
    }

    private void validateErrorFunction() throws IOException {
        System.out.println("Enter error function");
        readErrorFunction(fromKeyboard);
    }

    private void validateMatrix() throws IOException {
        System.out.println("Enter the matrix");
        readMatrix(fromKeyboard);
    }

    private void validateArrayRange() throws IOException {
        System.out.println("Enter the range of the matrix");
        readRange(fromKeyboard);
    }

    public void getMatrixFromFile() throws IOException {
        boolean check = true;
        do {
            System.out.println("Enter the file name:");
            String fileName = fromKeyboard.readLine();
            File file = new File(fileName);
            if (file.canRead() && file.exists()) {
                try {
                    InputStream inputStream = new FileInputStream(fileName);
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader fileReader = new BufferedReader(inputStreamReader);
                    readRange(fileReader);
                    a = new double[range][range];
                    b = new double[range];
                    readMatrix(fileReader);
                    readErrorFunction(fileReader);
                    fileReader.close();
                } catch (Exception e) {
                    check = false;
                    System.out.println("Error while read file please check the file again");
                }
            } else {
                check = false;
                System.out.println("File can't be read or not existed");
            }

        } while (check);
        matrix = new Matrix(range,a,b,erf);
    }

    private void readMatrix(BufferedReader fileReader) throws IOException {
        for (int i = 0; i < range; i++) {
            String[] matrixA = fileReader.readLine().split(" ");
            for (int j = 0; j < range; j++) {
                a[i][j] = Double.parseDouble(matrixA[j]);
            }
            b[i] = Double.parseDouble(matrixA[range]);
        }
    }

    private void readErrorFunction(BufferedReader fileReader) throws  IOException {
        erf = Double.parseDouble(fileReader.readLine());
    }

    private void readRange(BufferedReader fileReader) throws IOException {
        range = Integer.parseInt(fileReader.readLine());
    }

    public Matrix getMatrix() {
        return matrix;
    }
}
