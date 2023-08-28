package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.SuperDAO;

import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> getData();
}
