package test;

import main.App;

import static org.junit.Assert.*;

import main.Matrix;
import org.junit.Test;

public class Tester
{
    @Test
    public void testMultSerialNXN()
    {
        Matrix a = new Matrix(new float[][]
        {
            { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 8, 7, 6 },
            { 5, 4, 3, 2 },
        });

        Matrix b = new Matrix(new float[][]
        {
            { 5, 6, 7, 8 },
            { 8, 7, 6, 5 },
            { 9, 1, 1, 9 },
            { 8, 8, 6, 6 },
        });

        Matrix cRight = new Matrix(new float[][]
        {
            {  80,  55,  46,  69 },
            { 200, 143, 126, 181 },
            { 220, 165, 154, 211 },
            { 100,  77,  74,  99 },
        });

        Matrix c = Matrix.multSerial(a, b);

        assertEquals(c, cRight);
    }

    @Test
    public void testMultSerialNXM()
    {
        Matrix a = new Matrix(new float[][]
        {
            { 1, 2, 3 },
            { 5, 6, 7 },
            { 9, 8, 7 },
            { 5, 4, 3 },
        });

        Matrix b = new Matrix(new float[][]
        {
            { 5, 6, 7, 8 },
            { 8, 7, 6, 5 },
            { 9, 1, 1, 9 },
        });

        Matrix cRight = new Matrix(new float[][]
        {
            {  48,  23,  22,  45 },
            { 136,  79,  78, 133 },
            { 172, 117, 118, 175 },
            {  84,  61,  62,  87 },
        });

        Matrix c = Matrix.multSerial(a, b);

        assertEquals(c, cRight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncompatibleMatrices()
    {
        Matrix a = Matrix.createRandomized(5, 4);
        Matrix b = Matrix.createRandomized(10, 3);

        Matrix c = Matrix.multSerial(a, b);
    }

    @Test
    public void testRandomSerial()
    {
        int N = 64;

        Matrix a = Matrix.createRandomized(N, N+1);
        Matrix b = Matrix.createRandomized(N+1, N);

        Matrix c = Matrix.multSerial(a, b);
    }

    @Test
    public void testRandomParallel3()
    {
        int N = 64;

        Matrix a = Matrix.createRandomized(N, N+1);
        Matrix b = Matrix.createRandomized(N+1, N);

        Matrix cSerial = Matrix.multSerial(a, b);
        Matrix c = Matrix.multParallel3(a, b);

        assertEquals(cSerial, c);
    }

    @Test
    public void testRandomParallel4()
    {
        int N = 64;

        Matrix a = Matrix.createRandomized(N, N+1);
        Matrix b = Matrix.createRandomized(N+1, N);

        Matrix cSerial = Matrix.multSerial(a, b);
        Matrix c = Matrix.multParallel4(a, b);

        assertEquals(cSerial, c);
    }
}
