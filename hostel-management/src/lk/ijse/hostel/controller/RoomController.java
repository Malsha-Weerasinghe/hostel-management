package lk.ijse.hostel.controller;

import animatefx.animation.Shake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.RoomsBO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomController {

    @FXML
    private AnchorPane pane;

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
    private ImageView backBtn;

    @FXML
    private TextField qtyField;

    @FXML
    private Button saveBtn;

    @FXML
    private TableView<RoomDTO> tblRooms;

    RoomsBO roomsBO = (RoomsBO) BOFactory.getBoFactory().getBO(BOFactory.Type.ROOM);

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        makeEditableTxtField(false);
        deleteBtn.setDisable(true);
        cancelBtn.setDisable(true);
        saveBtn.setDisable(true);
        editBtn.setDisable(true);

        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
                deleteBtn.setDisable(true);
                cancelBtn.setDisable(true);
                saveBtn.setDisable(true);
                editBtn.setDisable(false);
            }
        });

//        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
//            loadRoomData(newValue);
//            makeEditableTxtField(false);
//        });

        loadRoomData("");
    }

    private void loadRoomData(String SearchID) {
        ObservableList<RoomDTO> list = FXCollections.observableArrayList();

        ArrayList<RoomDTO> roomsDTOS = roomsBO.getRoomsData();
        for (RoomDTO std : roomsDTOS) {
            if (std.getRoom_type_id().contains(SearchID) ||
                    std.getKey_money().contains(SearchID) ||
                    std.getType().contains(SearchID)) {
                RoomDTO roomsDTO = new RoomDTO(
                        std.getRoom_type_id(),
                        std.getType(),
                        std.getKey_money(),
                        std.getQty());
                list.add(roomsDTO);
            }
        }
        tblRooms.setItems(list);
    }

    private void makeEditableTxtField(boolean b) {
        //txtRoomTypeID.setEditable(b);
        TypeField.setEditable(b);
        moneyField.setEditable(b);
        qtyField.setEditable(b);
    }

    private void setData(RoomDTO newValue) {
        IdField.setText(newValue.getRoom_type_id());
        TypeField.setText(newValue.getType());
        moneyField.setText(newValue.getKey_money());
        qtyField.setText(String.valueOf(newValue.getQty()));
    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) throws IOException {
        clearFields();

    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            String idText = IdField.getText();

            RoomDTO roomsDTO = new RoomDTO();
            roomsDTO.setRoom_type_id(idText);

            Boolean isAdded = roomsBO.deleteRoom(roomsDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, " Room Deleted ! ").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, " Error ! ").show();
            }
        }

        loadRoomData("");

    }

    @FXML
    void editBtnOnAction(ActionEvent event) {
        if (!IdField.getText().equals("")) {
            deleteBtn.setDisable(false);
            cancelBtn.setDisable(false);
            saveBtn.setDisable(false);
            saveBtn.setText("Update");
            makeEditableTxtField(true);

        } else {
            new Alert(Alert.AlertType.ERROR, "Not selected !").show();
        }

    }

    @FXML
    void newBtnOnAction(ActionEvent event) {

        makeEditableTxtField(true);
        clearFields();

        editBtn.setDisable(true);
        deleteBtn.setDisable(true);
        cancelBtn.setDisable(false);
        saveBtn.setDisable(false);
        saveBtn.setText("Save");
        //String nextID = generateNextID(roomsBO.getCurrentID());
        //txtRoomTypeID.setText(nextID);
        IdField.requestFocus();
        IdField.setText("RM-");

    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        if (!IdField.getText().equals("") || TypeField.getText().equals("") || moneyField.getText().equals("")) {
            String roomTypeIDText = IdField.getText();
            String typeText = TypeField.getText();
            String keyMoneyText = moneyField.getText();
            int qtyText = Integer.parseInt(qtyField.getText());

            if (isValidRoomTypeID() && isValidType() && isValidKeyMoney() && isValidQTY()) {
                if (saveBtn.getText().equals("Save")) {
                    RoomDTO roomsDTO = new RoomDTO(roomTypeIDText, typeText, keyMoneyText, qtyText);
                    Boolean isAdded = roomsBO.addRoom(roomsDTO);

                    if (isAdded) {
                        new Alert(Alert.AlertType.INFORMATION, " Room Added ! ").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                    }
                }

                if (saveBtn.getText().equals("Update")) {
                    RoomDTO roomsDTO = new RoomDTO(roomTypeIDText, typeText, keyMoneyText, qtyText);
                    Boolean isUpdated = roomsBO.updateRoom(roomsDTO);

                    if (isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, " Room Updated ! ").show();
                        clearFields();
                    } else {
                        new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                        clearFields();
                    }
                }
                loadRoomData("");

            } else {
                new Alert(Alert.AlertType.WARNING, "Fill data !").show();
            }
        }
    }

    private boolean isValidQTY() {
        Pattern pattern = Pattern.compile("^[0-9]{1,}$");
        Matcher matcher = pattern.matcher(qtyField.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(qtyField);
            qtyField.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidKeyMoney() {
        Pattern pattern = Pattern.compile("^[0-9]{3,}$");
        Matcher matcher = pattern.matcher(moneyField.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(moneyField);
            moneyField.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidType() {
        Pattern pattern = Pattern.compile("^(AC|Non-AC|None)$");
        Matcher matcher = pattern.matcher(TypeField.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(TypeField);
            TypeField.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidRoomTypeID() {
        Pattern pattern = Pattern.compile("^(?:RM-)[0-9]{4}$");
        Matcher matcher = pattern.matcher(IdField.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(IdField);
            IdField.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    @FXML
    void backBtnOnAction(MouseEvent event)  throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);

    }

    private void clearFields() {
        IdField.clear();
        TypeField.clear();
        moneyField.clear();
        qtyField.clear();
    }
}
