package org.yyf.mybatisexmaple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.yyf.mybatisexmaple.domain.Color;
import org.yyf.mybatisexmaple.domain.Course;
import org.yyf.mybatisexmaple.domain.EnumTestDomain;
import org.yyf.mybatisexmaple.domain.Level;
import org.yyf.mybatisexmaple.mappers.CourseMapper;
import org.yyf.mybatisexmaple.mappers.EnumTestDomainMapper;
import org.yyf.mybatisexmaple.util.MyBatisSqlSessionFactory;

import java.util.*;

/**
 * Created by yeyf on 2014-12-18.
 */
public class CourseMapperTest {
    @Test
    public void getAllItemsTest() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
        Map paramMap = new HashMap();
//        paramMap.put("tutorId", 1);
//        paramMap.put("courseName", "%java%");
//        paramMap.put("startDate", new Date());
        List<Course> allItems = courseMapper.getCourses(paramMap);
        System.out.println(allItems);
        sqlSession.close();

    }
    @Test
    public void searchCoursesByTutorsTest() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Integer> tutorIds = new ArrayList<Integer>();
        tutorIds.add(1);
        tutorIds.add(3);
        tutorIds.add(6);
        map.put("tutorIds", tutorIds);
        CourseMapper mapper =sqlSession.getMapper(CourseMapper.class);
        List<Course> courses = mapper.searchCoursesByTutors(map);

    }
}
