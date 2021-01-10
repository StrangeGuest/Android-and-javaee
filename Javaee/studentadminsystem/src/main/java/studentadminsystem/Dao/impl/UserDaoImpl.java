package studentadminsystem.Dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import studentadminsystem.Dao.UserDao;
import studentadminsystem.model.User;
import studentadminsystem.util.C3p0Util;
import studentadminsystem.util.DBHelper;
import studentadminsystem.util.Md5Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    /*
    * user对象的增删改查，都在此处进行
    */
    public boolean checkLogin(studentadminsystem.model.User user){
        String sql="select count(*) as count from tbl_user where username=? and password=?";
       long count=0;
        QueryRunner query = new QueryRunner(C3p0Util.getConnection());
        try {
            //Map<String,Object> map=query.query(sql,new MapHandler(),new Object[]{user.getUserName(), Md5Util.stringToMD5(user.getPassword())});
            Map<String,Object> map=query.query(sql,new MapHandler(),new Object[]{user.getUserName(), user.getPassword()});
            count=(Long)map.get("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count>0;

    }

    @Override
    public boolean save(studentadminsystem.model.User u) {
        String sql="insert into tbl_user(username,password) values (?,?)";
        DBHelper db=new DBHelper();
        List<Object> params=new ArrayList<>();
        params.add(u.getUserName());
        params.add(u.getPassword());
        int result=db.update(sql,params);
        return result>0;
    }


}
