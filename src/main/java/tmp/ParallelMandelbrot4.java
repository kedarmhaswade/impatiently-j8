package tmp;

/**
 * Created by kmhaswade on 4/28/16.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ParallelMandelbrot4 extends Thread {

    final static int N = 4096;
    final static int CUTOFF = 100;

     static int[][] set = new int[N][N];

    public static void main(String[] args) throws Exception {

        // Calculate set
        long startTime = System.currentTimeMillis();

        ParallelMandelbrot4 thread0 = new ParallelMandelbrot4(0);
        ParallelMandelbrot4 thread1 = new ParallelMandelbrot4(1);
        ParallelMandelbrot4 thread2 = new ParallelMandelbrot4(2);
        ParallelMandelbrot4 thread3 = new ParallelMandelbrot4(3);

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();

        thread0.join();
        thread1.join();
        thread2.join();
        thread3.join();

        long endTime = System.currentTimeMillis();

        System.out.println("Calculation completed in "
                + (endTime - startTime) + " milliseconds");

        // Plot image
        BufferedImage img = new BufferedImage(N, N,
                BufferedImage.TYPE_INT_ARGB);

        // Draw pixels
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                int k = set[i][j];

                float level;
                if (k < CUTOFF) {
                    level = (float) k / CUTOFF;
                } else {
                    level = 0;
                }
                Color c = new Color(0, level, 0);  // Green
                img.setRGB(i, j, c.getRGB());
            }
        }

        // Print file
        ImageIO.write(img, "PNG", new File("Mandelbrot.png"));
    }

    private final int me;

    public ParallelMandelbrot4(int me) {
        this.me = me;
    }

    @Override
    public void run() {

        int begin, end;

        if (me == 0) {
            begin = 0;
            end = (N / 4) * 1;
        }
        else if (me == 1) {
            begin = (N / 4) * 1;
            end = (N / 4) * 2;
        }
        else if (me == 2) {
            begin = (N / 4) * 2;
            end = (N / 4) * 3;
        } else {  // me == 3
            begin = (N / 4) * 3;
            end = N;
        }

        for (int i = begin; i < end; i++) {
            for (int j = 0; j < N; j++) {

                double cr = (4.0 * i - 2 * N) / N;
                double ci = (4.0 * j - 2 * N) / N;

                double zr = cr, zi = ci;

                int k = 0;
                while (k < CUTOFF && zr * zr + zi * zi < 4.0) {

                    // z = c + z * z
                    double newr = cr + zr * zr - zi * zi;
                    double newi = ci + 2 * zr * zi;

                    zr = newr;
                    zi = newi;

                    k++;
                }

                set[i][j] = k;
            }
        }
    }
}
