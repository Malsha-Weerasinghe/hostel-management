package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;

public interface UserBO extends SuperBO {
    String getUser(String id);

    String getPassword(String id);

    boolean updateUser_Pw(String newUserName, String newPw);
}
