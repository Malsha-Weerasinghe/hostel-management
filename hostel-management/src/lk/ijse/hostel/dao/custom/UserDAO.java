package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.entity.User;

public interface UserDAO extends CrudDAO<User> {
    String getUser(String id);

    String getPassword(String id);

    boolean updateUser_Pw(String newUserName, String newPw);
}
