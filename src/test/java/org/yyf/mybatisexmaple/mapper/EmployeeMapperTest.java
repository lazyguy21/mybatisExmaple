package org.yyf.mybatisexmaple.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.yyf.mybatisexmaple.domain.Employee;
import org.yyf.mybatisexmaple.mappers.EmployeeMapper;
import org.yyf.mybatisexmaple.util.MyBatisSqlSessionFactory;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
        for (Long i = 0L; i < 1000000; i++) {
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

    /**
     * 双花括号是新建匿名内部类，然后在匿名内部类里面创建了一个动态代码块，
     * 然后再调用你想调用的方法，组装SQL
     * @throws Exception
     */
    @Test
    public void name() throws Exception {
//        insert into test.employee
//            (id, name, age, salary, lat, lng, create_time)
//        VALUES (
//            #{id},#{name},#{age},#{salary},#{lat},#{lng},#{createTime}
//        )
        String sq = new SQL().INSERT_INTO("test.employee")
                             .VALUES("id, name, age, salary, lat, lng, create_time",
                                 "#{id},#{name},#{age},#{salary},#{lat},#{lng},#{createTime}")
                             .toString();
        System.out.println(sq);
        String sql = new SQL() {{
            SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
            SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
            FROM("PERSON P");
            FROM("ACCOUNT A");
            INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
            INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
            WHERE("P.ID = A.ID");
            WHERE("P.FIRST_NAME like ?");
            OR();
            WHERE("P.LAST_NAME like ?");
            GROUP_BY("P.ID");
            HAVING("P.LAST_NAME like ?");
            OR();
            HAVING("P.FIRST_NAME like ?");
            ORDER_BY("P.ID");
            ORDER_BY("P.FULL_NAME");
        }}.toString();

        System.out.println(sql);
    }

    @Test
    public void name2() throws Exception {
        new HashMap() {{

        }};

    }
}
