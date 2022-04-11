import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        InputHandler inputHandler = new InputHandler();
        Matrix matrix = inputHandler.getMatrix();
        if (matrix == null) {
            System.err.println("Matrix is null");
        }
        {
            matrix.solve();
        }

    }
}
