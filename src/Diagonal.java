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

    //метод для строк, где максимальный элемент всего один
    public boolean findNewMatrix() {
        double sum;
        int k;                                       //количество максимальных элементов
        Integer[] swap = new Integer[array.length];
        Integer index = null;
        boolean isStrict = false;
        boolean f = false;
        for (int i = 0; i < size; i++) {
            max = 0;
            sum = 0;
            k = 0;
            for (int j = 0; j < size; j++) {
                sum += Math.abs(array[i][j]);        //сумма всех элементов строки(модулей)
                if (Math.abs(array[i][j]) > max) {   //поиск максимального элемента
                    max = Math.abs(array[i][j]);
                    index = j;                      //индекс, куда можно переместить текущую строку
                    k = 1;
                } else {
                    if (Math.abs(array[i][j]) == max && Math.abs(array[i][j]) != 0) {
                        k++;
                    }
                }
            }
            if (k == 1) {                           //если максмальный элемент всего 1
                if (sum - max > max) {              //преобладающего элемента нет -> false
                    return false;
                } else {                            //преобладающий элемент есть
                    if (sum - max < max) {
                        isStrict = true;            //хотя бы одна строка должна быть <
                    }
                    if (index != null)
                        swap[index] = i;
                }
            } else {
                System.out.println("Преобладающих элементов больше 2-х");
                //Вызов метода для частных случаев - перестановки
                return isConverge(array);
            }
        }
        if (isStrict) {                                 //создание нового массива
            for (int i = 0; i < size; i++) {
                System.arraycopy(array[swap[i]], 0, newMatrix[i], 0, size);
                newBMatrix[i] = bArray[swap[i]];
            }
            return true;
        } else{
            return false;
        }
    }

    /* Метод для проверки диагонального преобладания */
    private boolean isConverge(double[][] array) {
        boolean isConv = false;
        double[][] zamena = null;
        int i = 0;
        Permutation permutation = new Permutation(array);
        while (!isConv && i < permutation.getFactorial(array.length)) {
            i++;
            zamena = permutation.next();
            isConv = checkConverge(zamena);
        }
        if (isConv && zamena != null){
            newMatrix = zamena.clone();
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