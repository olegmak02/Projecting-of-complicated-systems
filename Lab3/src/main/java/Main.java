import java.sql.Connection;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Connection connection = ConnectionDB.getConnection();
        Data.init();

        Long begin = new Date().getTime();

        DatabaseWorker databaseWorker1 = new DatabaseWorker(connection);
        DatabaseWorker databaseWorker2 = new DatabaseWorker(connection);

        WorkingTask task1 = new WorkingTask(databaseWorker1, Data.names1);
        WorkingTask task2 = new WorkingTask(databaseWorker2, Data.names2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Long> f1 = executorService.submit(task1);
        Future<Long> f2 = executorService.submit(task2);

        try {
            System.out.println("Час виконання першого потоку: " + f1.get() + " мс");
            System.out.println("Час виконання другого потоку: " + f2.get() + " мс");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Час роботи програми (мс):");
        System.out.println(new Date().getTime() - begin);

        executorService.shutdown();
    }
}
