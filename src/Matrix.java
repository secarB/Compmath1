import static java.lang.Math.abs;

public class Matrix {
    private int range;
    private int M = 1000;
    private int k = 1;
    private double[][] a;
    private double[] b;
    private double[] x;
    private double[] d = new double[M];
    private double efr;
    private double maxD = 0;
    private double D;
    private double s;
    private double x_I;
    public Matrix(int range, double[][] a, double[] b, double efr) {
        this.range = range;
        this.a = a.clone();
        this.b = b.clone();
        this.efr = efr;
        this.x = new double[range];
        for (int i = 0; i < range; i++) {
            x[i] = 0;
        }
        System.out.println("Matrix is created ");
        getMatrix();
    }
    public void solve() {
        if (isConverge(a,b)) {
            startSeidel();
        } else {
            System.out.println("Can't achieve diagonal dominance");
        }
    }
    private void startSeidel() {
        maxD = 0;
        for (int i = 0; i < range; i++) {
            s = b[i];
            for (int j = 0; j < range; j++) {
                if (j != i)
                {
                    s -= a[i][j] * x[j];
                }
            }
            x_I = s / a[i][i];
            D = abs(x_I - x[i]);
            if (D > maxD) maxD = D;
            x[i] = x_I;
        }
        d[k - 1] = maxD;
        if (maxD < efr) {
            System.out.println(maxD);
            getVectorX(x);
            getIteration(k);
            getError(d);
        } else if (k < M) {
            k++;
            startSeidel();
        } else {
            System.out.println("Iterations diverge.");
        }
    }

    /* Метод для проверки диагонального преобладания */
    public boolean isConverge(double[][] array,double[] bArray) {
        Diagonal diagonal = new Diagonal(array, bArray);
        boolean f = diagonal.findNewMatrix();
        if (f) {
            a = diagonal.getNewMatrix().clone();
            b = diagonal.getNewBMatrix().clone();
            System.out.println("Matrix with diagonal dominance:");
            getMatrix();
        }
        return f;
    }

    /* Вывод вектора неизвестных x-ов */
    private void getVectorX(double[] x) {
        System.out.println("Solution X: ");
        for (int i = 0; i < range; i++) {
            System.out.printf("%10.4f", x[i]);
            System.out.println();
        }
    }

    /* Вывод количества итераций */
    private void getIteration(int k) {
        System.out.println("Number of iterations = " + k);
    }

    /*Вывод вектора погрешностей */
    private void getError(double[] d) {
        System.out.println("Error vector: ");
        for (int i = 0; i < k; i++) {
            System.out.printf("%10.10f", d[i]);
            System.out.println();
        }
    }

    private void getMatrix() {
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < range; j++) {
                System.out.printf("%6.1f", a[i][j]);
            }
            System.out.print(" | ");
            System.out.printf("%6.1f", b[i]);
            System.out.println();
        }
    }
}

