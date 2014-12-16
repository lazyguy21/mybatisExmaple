package org.yyf.mybatisexmaple.mappers;

import org.yyf.mybatisexmaple.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    List<Student> findAllStudents();

    List<Map> findAllStudentsToMap();

    Student findStudentById(Integer id);

    void insertStudent(Student student);
}