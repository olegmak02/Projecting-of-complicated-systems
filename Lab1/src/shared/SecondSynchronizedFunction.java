package shared;



import data.Data;
import util.Operations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;

// This thread calculate that expression: C = В * МT + D * MX * a

public class SecondSynchronizedFunction implements Callable<double[]> {
    @Override
    public double[] call() {
        double[] temp1;
        synchronized (Data.B) {
            synchronized (Data.MT) {
                temp1 = Operations.vectorMatrixMultiply(Data.B, Data.MT);
            }
        }

        double[] temp2;
        synchronized (Data.D) {
            synchronized (Data.MX) {
                temp2 = Operations.vectorMatrixMultiply(Data.D, Data.MX);
            }
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
