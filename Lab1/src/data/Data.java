package data;

import java.io.*;
import java.util.Random;

public class Data {
    private static final String PATH = ".\\data.txt";
    private static final int SIZE_1 = 280;
    private static final int SIZE_2 = 315;

    public static volatile double a;
    public static volatile double[] D = new double[SIZE_1];
    public static volatile double[] B = new double[SIZE_1];
    public static volatile double[][] MD = new double[SIZE_1][SIZE_1];
    public static volatile double[][] MX = new double[SIZE_1][SIZE_2];
    public static volatile double[][] MT = new double[SIZE_1][SIZE_2];
    public static volatile double[][] ME = new double[SIZE_2][SIZE_2];

    public static void init() {
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            line = br.readLine();
            a = Double.parseDouble(line);

            line = br.readLine();
            if (!line.equals("")) {
                System.exit(1);
            }

            line = br.readLine();
            String[] values = line.split(" ");
            for (int i = 0; i < SIZE_1; i++) {
                D[i] = Double.parseDouble(values[i]);
            }


            line = br.readLine();
            if (!line.equals(""))
                System.exit(1);

            line = br.readLine();
            String[] values2 = line.split(" ");
            for (int i = 0; i < SIZE_1; i++) {
                B[i] = Double.parseDouble(values2[i]);
            }


            line = br.readLine();
            if (!line.equals(""))
                System.exit(1);

            line = br.readLine();
            int i = 0;
            while (!line.equals("") && i < SIZE_1) {
                String[] values3 = line.split(" ");
                for (int j = 0; j < SIZE_1; j++) {
                    MD[i][j] = Double.parseDouble(values3[j]);
                }
                i++;
                line = br.readLine();
            }


            line = br.readLine();
            i = 0;
            while (!line.equals("") && i < SIZE_1) {
                String[] values4 = line.split(" ");
                for (int j = 0; j < SIZE_2; j++) {
                    MX[i][j] = Double.parseDouble(values4[j]);
                }
                i++;
                line = br.readLine();
            }


            line = br.readLine();
            i = 0;
            while (!line.equals("") && i < SIZE_1) {
                String[] values5 = line.split(" ");
                for (int j = 0; j < SIZE_2; j++) {
                    MT[i][j] = Double.parseDouble(values5[j]);
                }
                i++;
                line = br.readLine();
            }


            line = br.readLine();
            i = 0;
            while (!line.equals("") && i < SIZE_2) {
                String[] values6 = line.split(" ");
                for (int j = 0; j < SIZE_2; j++) {
                    ME[i][j] = Double.parseDouble(values6[j]);
                }
                i++;
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateNewData() {
        File f = new File(PATH);
        f.delete();
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random r = new Random();
        double max = 100.0;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            bw.write(String.valueOf(r.nextDouble() * max));
            bw.write("\n\n");
            bw.flush();

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < SIZE_1; i++) {
                builder.append(r.nextDouble() * max);
                builder.append(" ");
            }
            builder.append("\n\n");
            bw.write(builder.toString());
            bw.flush();


            StringBuilder builder2 = new StringBuilder();
            for (int i = 0; i < SIZE_1; i++) {
                builder2.append(r.nextDouble() * max);
                builder2.append(" ");
            }
            builder2.append("\n\n");
            bw.write(builder2.toString());
            bw.flush();


            StringBuilder builder3 = new StringBuilder();
            for (int i = 0; i < SIZE_1; i++) {
                builder3.append(r.nextDouble() * max);
                for (int j = 0; j < SIZE_1 - 1; j++) {
                    builder3.append(" ");
                    builder3.append(r.nextDouble() * max);
                }
                builder3.append("\n");
            }
            builder3.append("\n");
            bw.write(builder3.toString());
            bw.flush();

            StringBuilder builder4 = new StringBuilder();
            for (int i = 0; i < SIZE_1; i++) {
                builder4.append(r.nextDouble() * max);
                for (int j = 0; j < SIZE_2 - 1; j++) {
                    builder4.append(" ");
                    builder4.append(r.nextDouble() * max);
                }
                builder4.append("\n");
            }
            builder4.append("\n");
            bw.write(builder4.toString());
            bw.flush();

            StringBuilder builder5 = new StringBuilder();
            for (int i = 0; i < SIZE_1; i++) {
                builder5.append(r.nextDouble() * max);
                for (int j = 0; j < SIZE_2 - 1; j++) {
                    builder5.append(" ");
                    builder5.append(r.nextDouble() * max);
                }
                builder5.append("\n");
            }
            builder5.append("\n");
            bw.write(builder5.toString());
            bw.flush();

            StringBuilder builder6 = new StringBuilder();
            for (int i = 0; i < SIZE_2; i++) {
                builder6.append(r.nextDouble() * max);
                for (int j = 0; j < SIZE_2 - 1; j++) {
                    builder6.append(" ");
                    builder6.append(r.nextDouble() * max);
                }
                builder6.append("\n");
            }
            builder6.append("\n");
            bw.write(builder6.toString());
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
