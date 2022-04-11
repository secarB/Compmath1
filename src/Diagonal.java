import static java.lang.Math.abs;

public class Diagonal {

    private double[][] array;
    private int size;
    double max = 0;
    double[][] newMatrix;
    double [] newBMatrix;
    double [] bArray;
    public Diagonal(double[][] array, double[] bArray) {
        this.array = array.clone();
        this.bArray = bArray.clone();
        this.size = array.length;
        this.newMatrix = new double[size][size];
        this.newBMatrix = new double[size];
    }

    public boolean findNewMatrix() {
        double sum;
        int k;                                      
        Integer[] swap = new Integer[array.length];
        Integer index = null;
        boolean isStrict = false;
        boolean f = false;
        for (int i = 0; i < size; i++) {
            max = 0;
            sum = 0;
            k = 0;
            for (int j = 0; j < size; j++) {
                sum += Math.abs(array[i][j]);      
                if (Math.abs(array[i][j]) > max) {   
                    max = Math.abs(array[i][j]);
                    index = j;                     
                    k = 1;
                } else {
                    if (Math.abs(array[i][j]) == max && Math.abs(array[i][j]) != 0) {
                        k++;
                    }
                }
            }
            if (k == 1) {                           
                if (sum - max > max) {              
                    return false;
                } else {                          
                    if (sum - max < max) {
                        isStrict = true;       
                    }
                    if (index != null)
                        swap[index] = i;
                }
            } else {
                System.out.println("have 2 max elemnts 2-х");
                return isConverge(array);
            }
        }
        if (isStrict) {                           
            for (int i = 0; i < size; i++) {
                System.arraycopy(array[swap[i]], 0, newMatrix[i], 0, size);
                newBMatrix[i] = bArray[swap[i]];
            }
            return true;
        } else{
            return false;
        }
    }

    private boolean isConverge(double[][] array) {
        boolean isConv = false;
        double[][] zamena = null;
        int i = 0;
        Permutation permutation = new Permutation(array);
        while (!isConv && i < permutation.getFactorial(array.length)) {
            i++;
            alternate = permutation.next();
            isConv = checkConverge(alternate);
        }
        if (isConv && alternate != null){
            newMatrix = alternate.clone();
        }
        return isConv;
    }

    private boolean checkConverge(double[][] res) {
        double sum = 0;
        boolean isStrict = false;
        for (int i = 0; i < size; i++) {
            sum = 0 - abs(res[i][i]);
            for (int j = 0; j < size; j++) {
                sum = sum + abs(res[i][j]);
            }
            if (abs(res[i][i]) < sum) {
                return false;
            }
            else{
                if (abs(res[i][i]) > sum)
                    isStrict = true;
            }
        }
        return isStrict;
    }

    public double[] getNewBMatrix() {
        return newBMatrix;
    }

    public double[][] getNewMatrix() {
        return newMatrix;
    }
}
