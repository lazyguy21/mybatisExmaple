package org.yyf.mybatisexmaple.mappers;

import org.yyf.mybatisexmaple.domain.Course;

import java.util.List;
import java.util.Map;

/**
 * Created by yeyf on 2015-1-20.
 */
public interface CourseMapper {
    List<Course> getCourses(Map<String,Object> map);
    List<Course> searchCourses(Map<String,Object> map);
    List<Course> searchCoursesByTutors(Map<String, Object> map);
}
