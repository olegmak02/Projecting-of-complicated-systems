package unshared;

import data.Data;
import util.Operations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;

// This thread calculate that expression: MА = min(D + В) * MD * MT + MX * ME

public class FirstFunction implements Callable<double[][]> {
    @Override
    public double[][] call() {
        double[] temp1 = Operations.vectorAddition(Data.D, Data.B);
        double min = Arrays.stream(temp1).min().getAsDouble();
        double[][] temp2 = Operations.scalarMatrixMultiply(min, Data.MD);
        double[][] temp3 = Operations.matrixMultiply(temp2, Data.MT);
        double[][] temp4 = Operations.matrixMultiply(Data.MX, Data.ME);
        double[][] res = Operations.matrixAdding(temp3, temp4);
        try (FileWriter bw = new FileWriter(".\\output_unsynchronized_MA.txt")) {
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
