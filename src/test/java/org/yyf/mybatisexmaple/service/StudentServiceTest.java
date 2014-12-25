package org.yyf.mybatisexmaple.service;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yyf.mybatisexmaple.domain.Student;
import org.yyf.mybatisexmaple.mappers.StudentMapper;
import org.yyf.mybatisexmaple.util.MyBatisSqlSessionFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yeyf on 2014-12-9.
 */
public class StudentServiceTest {
    private static StudentService studentService;
    private static SqlSession sqlSession;
    @BeforeClass
    public static void setup() {
        studentService = new StudentService();
        sqlSession = MyBatisSqlSessionFactory.openSession();
    }

    @AfterClass
    public static void teardown() {
        studentService = null;
    }

    @Test
    public void testFindAkllStudents() {
        List<Student> students = studentService.findAllStudents();
        Assert.assertNotNull(students);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * 如果mysql是auto increament ，其实是通过jdbc的api getGenratedKey'得到的id
     * 具体jdbc是咋实现的？懒得管了
     */
    @Test
    public void insertStudentAutoID() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setDob("whatever");
        student.setEmail("关你啥事？");
        student.setName("叶三");
        student.setPhone("1a34r32a啊");
        mapper.insertStudentAutoID(student);
        System.out.println(student);
    }

    @Test
    public void testFindStudentById() {
        /**
         * 如果返回的数据大于一条则报错，底层使用的sqlsession的selectOne
         * 这和hibernate那个getUniqueResult什么的同样原理
         */
        Student student = studentService.findStudentById(null);
        Assert.assertNotNull(student);
        System.out.println(student);
    }

    @Test
    public void testGetStudentByIdToMap() {
        /**
         *我发现mybatis会根据你写的sql中返回的列名的大小写对应map中name的大小写，
         * 但实际jdbc的getColumnName是根据数据库不同而导致大小写不同的，
         * 比如oracle这种返回的所有列名统统大写
         * 这个优化的细节，nice~
         */
        Map map = sqlSession.selectOne("getStudentByIdToMap", 1);
        System.out.println(map);
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student();
        int id = 3;
        student.setId(id);
        student.setName("student_" + id);
        student.setEmail("student_" + id + "gmail.com");
        student.setDob(new Date().toLocaleString());
        studentService.createStudent(student);
        Student newStudent = studentService.findStudentById(id);
        Assert.assertNotNull(newStudent);
    }
}
