package org.yyf.mybatisexmaple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.yyf.mybatisexmaple.domain.Color;
import org.yyf.mybatisexmaple.domain.EnumTestDomain;
import org.yyf.mybatisexmaple.domain.Level;
import org.yyf.mybatisexmaple.mappers.EnumTestDomainMapper;
import org.yyf.mybatisexmaple.util.MyBatisSqlSessionFactory;

import java.util.List;

/**
 * Created by yeyf on 2014-12-18.
 */
public class EnumTestDomainMapperTest {
    @Test
    public void getAllItemsTest() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        EnumTestDomainMapper enumTestDomainMapper = sqlSession.getMapper(EnumTestDomainMapper.class);
        List<EnumTestDomain> allItems = enumTestDomainMapper.getAllItems();
        System.out.println(allItems);
        sqlSession.close();

    }

    @Test
    public void testSqlSession() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        List<Object> items = sqlSession.selectList("org.yyf.mybatisexmaple.mappers.EnumTestDomainMapper.getAllItems");
        System.out.println(items);
    }

    @Test
    public void insertEnumTestDomainTest() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        EnumTestDomain domain = new EnumTestDomain();
        domain.setId(2L);
        domain.setColor(Color.Blue);
        domain.setLevel(Level.MEDIUM);
        sqlSession.insert("insertEnumTestDomain", domain);
        sqlSession.commit();
        sqlSession.close();
    }
}
