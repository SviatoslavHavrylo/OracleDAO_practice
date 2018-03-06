package practiceDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SviatoslavHavrilo on 01.03.2018.
 */
public class DaoOracleImpl implements DaoModel {
    private static final DaoOracleImpl instance = new DaoOracleImpl();
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    private DaoOracleImpl() {
    }


    public static DaoOracleImpl getInstance() {
        return instance;
    }

    @Override
    public void connect() {
        try {
            //locals.setDefault
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Driver driver = new OracleDriver();

            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "1");
            if (!connection.isClosed()) {
                System.out.println("Successful connected");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null)
                connection.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (resultSet != null)
                resultSet.close();
            System.out.println("Disconnected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAllSudents() {
        List<Student> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM SH88.STUDENT");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(parseStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanStudentsTable() {
        try {
            preparedStatement = connection.prepareStatement("DELETE SH88.STUDENT");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Student student) {
        try {
            preparedStatement = connection.prepareStatement("INSERT Into SH88.STUDENT (ID, NAME , AGE, STUDENTGROUP) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getGroup());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE  SH88.STUDENT WHERE ID = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudentAgeById(int id, int age) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE SH88.STUDENT SET AGE = ? WHERE ID = ?");
            preparedStatement.setInt(1, age);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Student parseStudent(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String group = resultSet.getString("STUDENTGROUP");
        if (!(name == null)) name = name.trim();
        if (!(group == null)) group = group.trim();
        int age = resultSet.getInt("AGE");
        return new Student(id, name, group, age);
    }
}
