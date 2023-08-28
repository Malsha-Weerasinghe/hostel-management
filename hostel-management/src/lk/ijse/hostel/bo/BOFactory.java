package lk.ijse.hostel.bo;

import lk.ijse.hostel.bo.custom.impl.ReservationBOImpl;
import lk.ijse.hostel.bo.custom.impl.RoomsBOImpl;
import lk.ijse.hostel.bo.custom.impl.StudentBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum Type {
        STUDENT,
        ROOM,
        RECEPTION,
        USER
    }

    public SuperBO getBO(Type type) {
        switch (type) {
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomsBOImpl();
            case RECEPTION:
                return new ReservationBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
