package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.hostel.dto.RoomDTO;

public class RoomController {

    @FXML
    private TextField IdField;

    @FXML
    private TextField TypeField;

    @FXML
    private Button cancelBtn;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;

    @FXML
    private TextField moneyField;

    @FXML
    private Button newBtn;

    @FXML
    private TextField qtyField;

    @FXML
    private Button saveBtn;

    @FXML
    private TableView<RoomDTO> tblRooms;

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
    void saveBtnOnAction(ActionEvent event) {

    }
}
