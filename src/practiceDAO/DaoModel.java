package practiceDAO;

import java.util.List;

/**
 * Created by SviatoslavHavrilo on 01.03.2018.
 */
public interface DaoModel {
    void connect();
    void disconnect();
    List<Student> getAllSudents();
    void insert(Student student);
    void removeById (int id);
    void updateStudentAgeById(int id, int age);
}
