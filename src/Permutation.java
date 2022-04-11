import java.util.Arrays;

public class Permutation {
    private double[][] arr;             //исходная матрица
    private int[] sequence;             //массив индексов от 0 до n - строк
    private int size;                   //размер матрицы
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
            while (i >= 0 && sequence[i] == size - 1) {      //пока есть индексы, равные размеру матрицы - 1
                swap(i, sequence[i]);                        //меняем строки
                sequence[i] = i;                             //возвращаем элементу значение индекса
                i--;
            }
            if (i < 0)
                arr = null;
            else {
                swap(i, sequence[i]);                       //обмен следующего элемента с индесом
                sequence[i] ++ ;                            //меняем последовательность перестановки
                swap(i, sequence[i]);                       //получаем новую перестановку
            }
            return res;                                     //возвращаем массив
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
    /* Метод для перестановки - меняем местами i и j строки матрицы */
    private void swap(int i, int j) {
        double[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}