package studentadminsystem.Dao;

import studentadminsystem.model.User;

public interface UserDao {
    boolean checkLogin(User u);
    boolean save(User u);
}
