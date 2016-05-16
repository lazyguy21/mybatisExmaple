package org.yyf.mybatisexmaple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.yyf.mybatisexmaple.domain.Employee;
import org.yyf.mybatisexmaple.mappers.EmployeeMapper;
import org.yyf.mybatisexmaple.util.MyBatisSqlSessionFactory;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

/**
 * Created by ye on 15-7-14.
 */
public class EmployeeMapperTest {
    @Test
    public void testX() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        Employee temp = new Employee();
        Random random = new Random(10);
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Instant start = Instant.now();
        for (Long i = 0L; i < 5000000; i++) {
            temp.setId(i);
            temp.setName("tobi" + i);
            temp.setAge(random.nextInt(100));
            temp.setSalary(random.nextInt(10000) + random.nextDouble());
            temp.setLat(-89 + random.nextInt(89) + random.nextDouble());
            temp.setLng(-179 + random.nextInt(179) + random.nextDouble());
            temp.setCreateTime(new Date());
            mapper.insertEmployee(temp);
        }
        Instant end = Instant.now();
        System.out.println(start);
        System.out.println(end);
    }
}
