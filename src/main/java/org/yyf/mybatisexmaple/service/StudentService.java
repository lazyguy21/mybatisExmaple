package org.yyf.mybatisexmaple.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyf.mybatisexmaple.domain.Student;
import org.yyf.mybatisexmaple.mappers.StudentMapper;
import org.yyf.mybatisexmaple.util.MyBatisSqlSessionFactory;

import java.util.List;

public class StudentService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<Student> findAllStudents() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.findAllStudents();
        } finally {
            //If sqlSession is not closed
            //then database Connection associated this sqlSession will not be returned to pool
            //and application may run out of connections.
            sqlSession.close();
        }
    }

    public Student findStudentById(Integer studId) {
        logger.debug("Select Student By ID :{}", studId);
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try {
            StudentMapper studentMapper =
                    sqlSession.getMapper(StudentMapper.class);
            return studentMapper.findStudentById(studId);
        } finally {
            sqlSession.close();
        }
    }

    public void createStudent(Student student) {
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try {
            StudentMapper studentMapper =
                    sqlSession.getMapper(StudentMapper.class);
            studentMapper.insertStudent(student);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}