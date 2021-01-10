package studentadminsystem.Dao.impl;

import studentadminsystem.Dao.StudentDao;
import studentadminsystem.model.Student;
import studentadminsystem.util.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> getAllStudent() {
        String sql="select *from tbl_student";
        DBHelper db=new DBHelper();
        List<Object> result=db.query(sql,null);
        List<Student> students=new ArrayList<>();
        for(Object o:result){
            Map map=(Map)o;
            students.add(
                    new Student(
                            (String)map.get("stuno"),
                            (String)map.get("stuname"),
                            (String)map.get("classes"),
                            (String)map.get("gender"),
                            (String)map.get("department"),
                            (String)map.get("tel"),
                            (String)map.get("dormno"),
                            (String)map.get("photopath")
                    )
            );
        }
        return students;
    }

    @Override
    public boolean saveStudent2(Student s) {//增加学生信息
        String sql="insert into tbl_student(stuno,stuname,classes,gender,department,tel,dormno,photopath) values (?,?,?,?,?,?,?,?)";
        DBHelper db=new DBHelper();
        List<Object> params=new ArrayList<>();
        params.add(s.getStuNo());
        params.add(s.getStuName());
        params.add(s.getClasses());
        params.add(s.getGender());
        params.add(s.getDepartment());
        params.add(s.getTel());
        params.add(s.getDormNo());
        params.add(s.getPhotoPath());
        int result=db.update(sql,params);
        return result>0;
    }


    @Override
    public List<Student> getStudentByName(String name) {//按姓名查询学生信息
        String sql="select *from tbl_student where stuname like ?";
        DBHelper db=new DBHelper();
        List<Object> params=new ArrayList<>();
        params.add('%'+name+'%');
        List<Object> result=db.query(sql,params);
        List<Student> students=new ArrayList<>();
        for(Object o:result){
            Map map=(Map)o;
            students.add(
                    new Student(
                            (String)map.get("stuno"),
                            (String)map.get("stuname"),
                            (String)map.get("classes"),
                            (String)map.get("gender"),
                            (String)map.get("department"),
                            (String)map.get("tel"),
                            (String)map.get("dormno"),
                            (String)map.get("photopath")
                    )
            );
        }
        return students;
    }

    @Override
    public Student getStudentByNo(String stuno) { //按学号查询
        String sql="select *from tbl_student where stuno='"+stuno+"'";
        List<Object> query = new DBHelper().query(sql, null);
        Map map=(Map)query.get(0);
        return  new Student(
                (String)map.get("stuno"),
                (String)map.get("stuname"),
                (String)map.get("classes"),
                (String)map.get("gender"),
                (String)map.get("department"),
                (String)map.get("tel"),
                (String)map.get("dormno"),
                (String)map.get("photopath")
        );
    }

    @Override
    public boolean updataStudent(Student s) { //修改学生信息
        String sql="update tbl_student set stuname=? ,classes=?, gender=?, department=?, tel=?, dormno=?"
                +"where stuno='"+s.getStuNo()+"'";
        DBHelper db=new DBHelper();
        List<Object> params=new ArrayList<>();
        params.add(s.getStuName());
        params.add(s.getClasses());
        params.add(s.getGender());
        params.add(s.getDepartment());
        params.add(s.getTel());
        params.add(s.getDormNo());
        int result=db.update(sql,params);
        return result>0;
    }

    @Override
    public List<Student> getStudentByPaging(int currentPage, int pageSize) {
        String sql="select *from tbl_student where stuno limit ?,?";
        int start=(currentPage-1)*pageSize;
        DBHelper dbHelper = new DBHelper();
        List<Object> params=new ArrayList<>();
        params.add(start);
        params.add(pageSize);
        List<Object> result=dbHelper.query(sql,params);
        List<Student> students=new ArrayList<>();
        for(Object o:result){
            Map map=(Map)o;
            students.add(
                    new Student(
                            (String)map.get("stuno"),
                            (String)map.get("stuname"),
                            (String)map.get("classes"),
                            (String)map.get("gender"),
                            (String)map.get("department"),
                            (String)map.get("tel"),
                            (String)map.get("dormno"),
                            (String)map.get("photopath")
                    )
            );
        }
        return students;
    }

    @Override
    public long totalRecords() {
        String sql="select count(*) as count from tbl_student";
        DBHelper dbHelper = new DBHelper();
        List<Object>query=dbHelper.query(sql,null);
        return (long) ((Map)query.get(0)).get("count");
    }

    @Override
    public boolean deleteStudentByNo(String stuno) {
        String sql="delete from tbl_student where stuno=?";
        DBHelper db=new DBHelper();
        List<Object> params=new ArrayList<>();
        params.add(stuno);
        int result=db.update(sql,params);
        return result>0;


    }


}
