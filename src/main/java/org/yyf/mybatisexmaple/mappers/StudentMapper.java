package org.yyf.mybatisexmaple.mappers;

import org.yyf.mybatisexmaple.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    List<Student> findAllStudents();

    public List<Map> findAllStudentsToMap();

    public Student findStudentById(Integer id);

    public Map getStudentByIdToMap(Integer id);
    public Student findStudentByIdWithAddress(Integer id);
    public Student findStudentWithAddressUpdated(Integer id);

    public void insertStudent(Student student);

    public void insertStudentAutoID(Student student);
    public void updateStudent(Student student);
    public List<Student> listPagedStudent();
}