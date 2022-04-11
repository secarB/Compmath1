import java.util.Arrays;

public class Permutation {
    private double[][] arr;            
    private int[] sequence;            
    private int size;               
    public Permutation(double[][] arr) {
        this.arr = arr.clone();
        size = arr.length;
        this.sequence = new int[size];
        for (int i = 0; i < size; i++)
            sequence[i] = i;
    }

    public double[][] next() {
        if (arr != null) {
            System.out.println();
            double[][] res = Arrays.copyOf(arr, size);
            int i = size - 1;
            while (i >= 0 && sequence[i] == size - 1) {     
                swap(i, sequence[i]);                      
                sequence[i] = i;                          
                i--;
            }
            if (i < 0)
                arr = null;
            else {
                swap(i, sequence[i]);                    
                sequence[i] ++ ;                      
                swap(i, sequence[i]);                      
            }
            return res;                                    
        }
        return null;
    }

    public int getFactorial(int f) {
        if (f <= 1) {
            return 1;
        } else {
            return f * getFactorial(f - 1);
        }
    }
    private void swap(int i, int j) {
        double[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
