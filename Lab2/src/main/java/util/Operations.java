package util;

public class Operations {
    public static double[][] matrixMultiply(double[][] matrix1, double[][] matrix2) {
        int a = matrix1.length;
        int b = matrix2[0].length;
        int c = matrix1[0].length;
        double[][] result = new double[a][b];

        for (int i = 0; i < a; i++) {
            for(int j = 0; j < b; j++) {
                double accumulator = 0;
                for (int k = 0; k < c; k++) {
                    accumulator += matrix1[i][k] * matrix2[k][j];
                }
                result[i][j] = accumulator;
            }
        }
        return result;
    }

    public static double[] vectorAddition(double[] vec1, double[] vec2) {
        int a = vec1.length;
        double[] sum = new double[a];
        for (int i = 0; i < a; i++) {
            sum[i] = vec1[i] + vec2[i];
        }
        return sum;
    }

    public static double[][] scalarMatrixMultiply(double scalar, double matrix[][]) {
        int a = matrix.length;
        int b = matrix[0].length;
        double[][] result = new double[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }
        return result;
    }

    public static double[][] matrixAdding(double matrix1[][], double matrix2[][]) {
        int a = matrix1.length;
        int b = matrix1[0].length;
        double[][] result = new double[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public static double[] vectorMatrixMultiply(double[] vector, double[][] matrix) {
        int a = matrix.length;
        int b = matrix[0].length;
        double[] res = new double[b];
        for (int i = 0; i < b; i++) {
            double accumulator = 0;
            for (int j = 0; j < a; j++) {
                accumulator += vector[j] * matrix[j][i];
            }
            res[i] = accumulator;
        }
        return res;
    }

    public static double[] vectorSubtracting(double[] vector1, double[] vector2) {
        int size = vector1.length;
        double[] res = new double[size];
        for (int i = 0; i < size; i++) {
            res[i] = vector1[i] - vector2[i];
        }
        return res;
    }

    public static double[] vectorScalarMultiply(double[] vec, double a) {
        int size = vec.length;
        double[] res = new double[size];
        for (int i = 0; i < a; i++) {
            res[i] = vec[i] * a;
        }
        return res;
    }
}
