package studentadminsystem.Dao;

import studentadminsystem.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    List<Student> getAllStudent() throws SQLException;

    boolean saveStudent2(Student s);

    List<Student> getStudentByName(String name);

    Student getStudentByNo(String stuno);

    boolean updataStudent(Student s);

    List<Student> getStudentByPaging(int currentPage, int pageSize);

    long totalRecords();

    boolean deleteStudentByNo(String stuno);
}
