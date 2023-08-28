package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;

public class ReservationController {

    @FXML
    private TextField StuIdField;

    @FXML
    private RadioButton allBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TableColumn<?, ?> colMoney;

    @FXML
    private TableColumn<?, ?> colPayStatus;

    @FXML
    private TableColumn<?, ?> colResDate;

    @FXML
    private TableColumn<?, ?> colResId;

    @FXML
    private TableColumn<?, ?> colRommType;

    @FXML
    private TableColumn<?, ?> colRoomDId;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colRoomMoney;

    @FXML
    private TableColumn<?, ?> colRoomQty;

    @FXML
    private TableColumn<?, ?> colSId;

    @FXML
    private TableColumn<?, ?> colSName;

    @FXML
    private TableColumn<?, ?> colStuId;

    @FXML
    private TableColumn<?, ?> colStuName;

    @FXML
    private TableColumn<?, ?> colTypeRoomId;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button newBtn;

    @FXML
    private RadioButton paidBtn;

    @FXML
    private RadioButton paidStatusBtn;

    @FXML
    private RadioButton pendingBtn;

    @FXML
    private RadioButton pendingStatusBtn;

    @FXML
    private DatePicker resDateField;

    @FXML
    private TextField resIdField;

    @FXML
    private Button reserveBtn;

    @FXML
    private ImageView backBtn;

    @FXML
    private TextField rooIdField;

    @FXML
    private TableView<ReservationDTO> tblReservation;

    @FXML
    private TableView<RoomDTO> tblRoom;

    @FXML
    private TableView<StudentDTO> tblStudent;

    @FXML
    void cancelBtnOnAction(ActionEvent event) {

    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void editBtnOnAction(ActionEvent event) {

    }

    @FXML
    void newBtnOnAction(ActionEvent event) {

    }

    @FXML
    void reserveBtnOnAction(ActionEvent event) {

    }

    @FXML
    void backBtnOnAction(MouseEvent event) {

    }
}
