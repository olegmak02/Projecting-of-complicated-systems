package shared;


import data.Data;
import util.Operations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;

// This thread calculate that expression: MА = min(D + В) * MD * MT + MX * ME

public class FirstSynchronizedFunction implements Callable<double[][]> {
    @Override
    public double[][] call() {
        double[][] temp1;
        synchronized (Data.MT) {
            synchronized (Data.MD) {
                temp1 = Operations.matrixMultiply(Data.MD, Data.MT);

            }
        }

        double[] temp2;
        synchronized (Data.D) {
            synchronized (Data.B) {
                temp2 = Operations.vectorAddition(Data.D, Data.B);
            }
        }

        double min = Arrays.stream(temp2).min().getAsDouble();


        double[][] temp3;
        synchronized (Data.MX) {
            synchronized (Data.ME) {
                temp3 = Operations.matrixMultiply(Data.MX, Data.ME);
            }
        }

        double[][] temp4 = Operations.scalarMatrixMultiply(min, temp1);
        double[][] res = Operations.matrixAdding(temp3, temp4);


        try (FileWriter bw = new FileWriter(".\\output_synchronized_MA.txt")) {
            for (double[] row: res) {
                for (double elem: row) {
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
