package DB;

/**
 * Created by ANIL on 27/12/2017.
 */
public class Subject {
    private String id;
    private String name;
    private DB.CSClass CSClass;
    private DB.Teacher Teacher;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DB.CSClass getCSClass() {
        return CSClass;
    }

    public void setCSClass(DB.CSClass CSClass) {
        this.CSClass = CSClass;
    }

    public DB.Teacher getTeacher() {
        return Teacher;
    }

    public void setTeacher(DB.Teacher teacher) {
        this.Teacher = teacher;
    }
}
