package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.bo.custom.RoomsBO;
import lk.ijse.hostel.dao.FactoryDAO;
import lk.ijse.hostel.dao.custom.RoomsDAO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.entity.Room;

import java.util.ArrayList;

public class RoomsBOImpl implements RoomsBO {

    RoomsDAO roomDAO = (RoomsDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.Types.ROOM);

    @Override
    public Boolean addRoom(RoomDTO roomsDTO) {
        Room room = new Room(
                roomsDTO.getRoom_type_id(),
                roomsDTO.getType(),
                roomsDTO.getKey_money(),
                roomsDTO.getQty());

        return roomDAO.add(room);
    }

    @Override
    public Boolean deleteRoom(RoomDTO roomsDTO) {
        return roomDAO.delete(roomsDTO.getRoom_type_id());
    }

    @Override
    public ArrayList<RoomDTO> getRoomsData() {
        ArrayList<RoomDTO> RoomDTOs = new ArrayList<>();
        ArrayList<Room> roomData = roomDAO.getData();

        for (Room r : roomData) {
            RoomDTOs.add(new RoomDTO(
                    r.getRoom_type_id(),
                    r.getType(),
                    r.getKey_money(),
                    r.getQty()));
        }
        return RoomDTOs;
    }

    public String getCurrentID() {
        return roomDAO.getCurrentID();
    }

    @Override
    public Boolean updateRoom(RoomDTO roomsDTO) {
        Room room = new Room(
                roomsDTO.getRoom_type_id(),
                roomsDTO.getType(),
                roomsDTO.getKey_money(),
                roomsDTO.getQty());

        return roomDAO.update(room);
    }
}
