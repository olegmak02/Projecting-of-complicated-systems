package expressions;

import data.Data;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Data.init();
        Lock lock = new ReentrantLock();
        FirstFunction firstTask = new FirstFunction(lock);
        SecondFunction secondTask = new SecondFunction(lock);

        ExecutorService executors = Executors.newFixedThreadPool(2);

        Date begin = new Date();

        Future<double[][]> firstResult = executors.submit(firstTask);
        Future<double[]> secondResult = executors.submit(secondTask);

        double[][] res1 = firstResult.get();
        double[] res2 = secondResult.get();

        Long dif = new Date().getTime() - begin.getTime();

        System.out.println("MA\n");
        for (double[] row: res1) {
            for (double elem: row) {
                System.out.print(elem + " ");
            }
            System.out.print("\n");
        }
        System.out.println("\n\n\n\n");

        System.out.println("C\n");
        for (double elem: res2) {
            System.out.println(elem);
        }

        System.out.println("Working time (shared data): " + dif);
        executors.shutdown();
    }
}
