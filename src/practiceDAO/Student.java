package practiceDAO;

/**
 * Created by SviatoslavHavrilo on 01.03.2018.
 */
public class Student {
    private int id;
    private String name;
    private String group;
    private int age;

    public Student(int id, String name, String group, int age) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.age = age;
    }

    public Student(int id, String name, String group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student " +name + " study in group "+ group + " Age: "+ age+ " ID = " + id;
    }
}
