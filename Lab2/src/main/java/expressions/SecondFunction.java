package expressions;



import data.Data;
import util.Operations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

// This thread calculate that expression: C = В * МT + D * MX * a

public class SecondFunction implements Callable<double[]> {
    private Lock lock;
    SecondFunction(Lock lock) {
        this.lock = lock;
    }

    @Override
    public double[] call() {
        double[] temp1;

        lock.lock();
        try {
            temp1 = Operations.vectorMatrixMultiply(Data.B, Data.MT);
        } finally {
            lock.unlock();
        }

        lock.lock();
        double[] temp2;
        try {
            temp2 = Operations.vectorMatrixMultiply(Data.D, Data.MX);
        } finally {
            lock.unlock();
        }

        double[] temp3 = Operations.vectorScalarMultiply(temp2, Data.a);

        double[] res = Operations.vectorAddition(temp1, temp3);

        try (FileWriter bw = new FileWriter(".\\output_synchronized_C.txt")) {
            for (double elem: res) {
                bw.write(elem + "\n");
            }
            bw.write("\n\n\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
