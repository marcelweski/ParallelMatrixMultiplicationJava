package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class App
{
    public static void main(String[] args)
    {
        /*
            Results

               N       time seq    time par3    time par4   speedup3   speedup4
              64        2,44 ms     14,06 ms      1,90 ms       0,17       1,29
             128       10,68 ms     24,54 ms      2,24 ms       0,44       4,76
             256       27,65 ms     33,94 ms      7,34 ms       0,81       3,77
             512      206,34 ms     70,79 ms     31,53 ms       2,91       6,54
            1024     2046,71 ms    249,26 ms    135,56 ms       8,21      15,10
            2048    44873,42 ms   1362,23 ms   1090,91 ms      32,94      41,13
            4096   433737,82 ms  11397,14 ms   9498,48 ms      38,06      45,66

         */

        Timer timer = new Timer();

        final int N = 1024;
        final int M = N;

        Matrix a = Matrix.createRandomized(N, M);
        Matrix b = Matrix.createRandomized(M, N);

        timer.start();
        Matrix cSerial = Matrix.multSerial(a, b);
        double timeSerial = timer.getMilliseconds();
        System.out.printf("timeSerial: %.2f ms\n", timeSerial);

        timer.start();
        Matrix cParallel3 = Matrix.multParallel3(a, b);
        double timeParallel3 = timer.getMilliseconds();
        System.out.printf("timeParallel3: %.2f ms\n", timeParallel3);

        timer.start();
        Matrix cParallel4 = Matrix.multParallel4(a, b);
        double timeParallel4 = timer.getMilliseconds();
        System.out.printf("timeParallel4: %.2f ms\n", timeParallel4);

        double speedup3 = timeSerial / timeParallel3;
        System.out.printf("speedup3: %.2f\n", speedup3);

        double speedup4 = timeSerial / timeParallel4;
        System.out.printf("speedup4: %.2f\n", speedup4);

        System.out.printf("%.2f ms  %.2f ms  %.2f ms  %.2f  %.2f\n",
                timeSerial,
                timeParallel3, timeParallel4,
                speedup3, speedup4);

        assertEquals(cSerial, cParallel3);
        assertEquals(cSerial, cParallel4);
    }
}
