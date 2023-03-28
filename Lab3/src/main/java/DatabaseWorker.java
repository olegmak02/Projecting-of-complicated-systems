import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseWorker {
    private Connection connection;

    public DatabaseWorker(Connection connection) {
        this.connection = connection;
    }

    public void execute(List<String> data, int baseIndex) {
        try {
            String insertTemplate = "INSERT INTO students (names) VALUES (?);";
            PreparedStatement statement = this.connection.prepareStatement(insertTemplate);

            for (int i = 0; i < 500; i++) {
                statement.setString(1, data.get(baseIndex + i));
                statement.executeUpdate();
            }

            String s = data.subList(baseIndex, baseIndex + 500).stream().collect(Collectors.joining("', '", "('", "');"));
            String selectTemplate = "SELECT * FROM students WHERE names in " + s;
            connection.createStatement().executeQuery(selectTemplate);

            Statement statement2 = connection.createStatement();
            statement2.execute("DELETE FROM students;");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
