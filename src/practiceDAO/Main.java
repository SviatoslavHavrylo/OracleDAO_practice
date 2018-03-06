package practiceDAO;

import java.util.List;

/**
 * Created by SviatoslavHavrilo on 01.03.2018.
 */
public class Main {
    public static void main(String[] args) {
        DaoOracleImpl inst = DaoOracleImpl.getInstance();

        inst.connect();

        inst.cleanStudentsTable();

        Student stAnna = new Student(1, "Anna", "31-1", 19);
        Student stViktor = new Student(2, "Viktor", "51-1", 22);
        Student stVika = new Student(3, "Vika", "12-1", 18);

        inst.insert(stAnna);
        inst.insert(stViktor);
        inst.insert(stVika);

        List<Student> list = inst.getAllSudents();
        for (Student student : list) {
            System.out.println(student.toString());
        }

        System.out.println("Remove student with ID 3");
        inst.removeById(3);
        System.out.println("Set Viktor's age 25");
        inst.updateStudentAgeById(2, 25);

        System.out.println("Actual students in database:");

        list = inst.getAllSudents();
        for (Student student : list) {
            System.out.println(student.toString());
        }
        inst.disconnect();
    }
}
