package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.dto.RoomDTO;

import java.util.ArrayList;

public interface RoomsBO extends SuperBO {
    Boolean addRoom(RoomDTO roomsDTO);

    Boolean deleteRoom(RoomDTO roomsDTO);

    ArrayList<RoomDTO> getRoomsData();

    //String getCurrentID();

    Boolean updateRoom(RoomDTO roomsDTO);
}
