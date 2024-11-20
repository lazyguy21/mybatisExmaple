package org.yyf.mybatisexmaple.jdbc;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by @author yyf on 2024/11/19.
 */
@Slf4j
public class BatchDemo {

  Connection conn = null;
  @Before
  public void beforeClass() throws Exception {
    // 注册mysql数据库jdbc连接驱动，
    //高版本的mysql底层使用了SSL协议加密，会导致wireshark抓到的数据包不是明文，所以在这配置不加密，方便观察底层文本。
    //rewriteBatchedStatements=true，配置后才能真的打开mysql批量操作功能
    String url = "jdbc:mysql://localhost:3306/test?useSSL=false&&rewriteBatchedStatements=true";
    String user = "root";
    String password = "rootroot";
    conn = DriverManager.getConnection(url, user, password);
  }

  @Test
  public void testAdd() throws SQLException {
    String SQL = "insert into student(id, stu_no, name) VALUES (?,?,?)";
    PreparedStatement pstmt = conn.prepareStatement(SQL);

    for (int i = 1; i < 20000; i++) {
      pstmt.setLong(1, i);
      pstmt.setInt(2, i);
      pstmt.setString(3, "张三" + i);
      pstmt.addBatch();
      //每1000个sql执行一次。
      if (i % 1000 == 0) {
        int[] count = pstmt.executeBatch();
        //每个sql执行成功后影响的数据行数。和单个执行的sql的返回意义一样
        System.out.println("count:" + Arrays.toString(count));
        pstmt.clearBatch();
      }

    }

  }

  @Test
  public void testUpdate() throws SQLException {
      String SQL = "update student set name=? where id=?";
      PreparedStatement pstmt = conn.prepareStatement(SQL);
      for (int i = 1; i < 11; i++) {
        pstmt.setString(1, "李四");
        pstmt.setLong(2, i);
        pstmt.addBatch();
      }
      int[] count = pstmt.executeBatch();
      System.out.println("count:" + Arrays.toString(count));

  }

  @Test
  public void testUpdateException() {
    try {
      String SQL = "update student set name=? where id=?";

      PreparedStatement pstmt = conn.prepareStatement(SQL);

      for (int i = 1; i < 11; i++) {
        if (i == 5) {
          //第五个SQL的时候name长度11,过长，会导致sql报错。
          pstmt.setString(1, "张三张三张三张三张三张");
        } else {
          pstmt.setString(1, "张三");
        }
        pstmt.setLong(2, i);
        pstmt.addBatch();
      }
      int[] count = pstmt.executeBatch();
      System.out.println("count:" + Arrays.toString(count));
    } catch (BatchUpdateException e) {
      System.out.println(e.getMessage());
      System.out.println("count:" + Arrays.toString(e.getUpdateCounts()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void testUpdateExceptionTransaction() {
    try {
      String SQL = "update student set name=? where id=?";
      conn.setAutoCommit(false);
      PreparedStatement pstmt = conn.prepareStatement(SQL);
      for (int i = 1; i < 11; i++) {
        if (i == 5) {
          pstmt.setString(1, "张三张三张三张三张三张");
        } else {
          pstmt.setString(1, "张三");
        }
        pstmt.setLong(2, i);
        pstmt.addBatch();
      }
      int[] count = pstmt.executeBatch();
      conn.commit();
      System.out.println("count:" + Arrays.toString(count));
    } catch (BatchUpdateException e) {
      System.out.println(e.getMessage());
      System.out.println("count:" + Arrays.toString(e.getUpdateCounts()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void t() throws SQLException {
    // Create SQL statement
    String SQL = "update student set name=? where id=?";

// Create PrepareStatement object
    PreparedStatement pstmt = conn.prepareStatement(SQL);

//Set auto-commit to false
//    conn.setAutoCommit(false);
    for (int i = 1; i < 20000; i++) {
      pstmt.setString(1, "张三" + i);
      pstmt.setLong(2, i);

      pstmt.addBatch();
      if (i % 1000 == 0) {
        int[] count = pstmt.executeBatch();
        pstmt.clearBatch();
      }
    }

//Create an int[] to hold returned values
    int[] count = pstmt.executeBatch();

//Explicitly commit statements to apply changes
//    conn.commit();
  }

  @Test
  public void name() throws SQLException {
    for (int i = 1; i < 100; i++) {
      String SQL = "insert into student(id, stu_no, name) VALUES (" + i + "," + i + ",'asfd')";

      PreparedStatement preparedStatement = conn.prepareStatement(SQL);
      preparedStatement.execute();
    }


  }
}
