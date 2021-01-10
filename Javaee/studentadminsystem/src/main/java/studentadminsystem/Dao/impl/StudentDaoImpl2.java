package studentadminsystem.Dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import studentadminsystem.Dao.StudentDao;
import studentadminsystem.model.Student;
import studentadminsystem.util.C3p0Util;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StudentDaoImpl2 implements StudentDao {
    @Override
    public List<Student> getAllStudent() throws SQLException {
        String sql="select *from tbl_student";
        QueryRunner queryRunner = new QueryRunner(C3p0Util.getConnection());
        List<Student> students = queryRunner.query(sql, new BeanListHandler<Student>(Student.class));
        return students;
    }

    @Override
    public boolean saveStudent2(Student s) {
        return false;
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return null;
    }

    @Override
    public Student getStudentByNo(String stuno) {
        return null;
    }

    @Override
    public boolean updataStudent(Student s) {
        return false;
    }

    @Override
    public List<Student> getStudentByPaging(int currentPage, int pageSize) {
        String sql="select * from tbl_student where stuno limit ?,?";
        int start=(currentPage-1)*pageSize;
        QueryRunner queryRunner = new QueryRunner(C3p0Util.getConnection());
        try{
            /*
            * 如何才能把ResultSet转换成bean?
            * 1.java reflect,Student.class 动态创建Student 对象，动态获取方法
            * 2.jdbc meta ,获取表的字段名称age
            * 3.根据字段名称,来调用Student的setter方法
            * */
            List<Student>students=queryRunner.query(sql,new BeanListHandler<Student>(Student.class),new Object[]{start,pageSize});
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long totalRecords() {
        String sql="select count(*) as count from tbl_student";
        long result=0;
        QueryRunner queryRunner = new QueryRunner(C3p0Util.getConnection());
        try{
            List<Map<String,Object>> query=queryRunner.query(sql,new MapListHandler());
             result=(Long)query.get(0).get("count");
            /*
            * block scope 块作用域,在块内定义的变量，在块外不能方法
            * */
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteStudentByNo(String stuno) {
        return false;
    }
}
