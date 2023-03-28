import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

public class WorkingTask implements Callable<Long> {
    private DatabaseWorker databaseWorker;
    private List<String> data;
    private int iterations = 15;

    public WorkingTask(DatabaseWorker databaseWorker, List<String> data) {
        this.databaseWorker = databaseWorker;
        this.data = data;
    }

    @Override
    public Long call() {
        Long begin = new Date().getTime();
        for (int i = 0; i < this.iterations; i++) {
            this.databaseWorker.execute(data, i);
        }
        return new Date().getTime() - begin;
    }
}
