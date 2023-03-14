package expressions;


import data.Data;
import util.Operations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

// This thread calculate that expression: MА = min(D + В) * MD * MT + MX * ME

public class FirstFunction implements Callable<double[][]> {
    private Lock lock;

    FirstFunction(Lock lock) {
        this.lock = lock;
    }

    @Override
    public double[][] call() {
        double[][] temp1;
        lock.lock();
        try {
            temp1 = Operations.matrixMultiply(Data.MD, Data.MT);
        } finally {
            lock.unlock();
        }


        double[] temp2;
        lock.lock();
        try {
            temp2 = Operations.vectorAddition(Data.D, Data.B);
        } finally {
            lock.unlock();
        }

        double min = Arrays.stream(temp2).min().getAsDouble();

        double[][] temp3;
        lock.lock();
        try {
            temp3 = Operations.matrixMultiply(Data.MX, Data.ME);
        } finally {
            lock.unlock();
        }

        double[][] temp4 = Operations.scalarMatrixMultiply(min, temp1);
        double[][] res = Operations.matrixAdding(temp3, temp4);

        try (FileWriter bw = new FileWriter(".\\output_synchronized_MA.txt")) {
            for (double[] row : res) {
                for (double elem : row) {
                    bw.write(String.valueOf(elem));
                    bw.write(" ");
                }
                bw.write("\n");
            }
            bw.write("\n\n\n\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}