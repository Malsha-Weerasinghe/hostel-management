package lk.ijse.hostel.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.dto.CustomDTO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ReservationController {

    @FXML
    private ToggleGroup FilterPayment;

    @FXML
    private ToggleGroup PaymentStatus;

    @FXML
    private AnchorPane newReservationPane;

    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane reservationPane;

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
    private TableView<CustomDTO> tblReservation;

    @FXML
    private TableView<RoomDTO> tblRoom;

    @FXML
    private TableView<StudentDTO> tblStudent;

    @FXML
    private Label txtSearchRoom;

    @FXML
    private Label txtSearchStu;

    @FXML
    private Label txtSelectRoom;

    @FXML
    private Label txtSelectStu;


    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.Type.RECEPTION);

    StudentDTO studentDTO;
    RoomDTO roomDTO;

    public void initialize() {
        newReservationPane.setDisable(true);
        reservationPane.setDisable(false);
        resIdField.setEditable(false);
        rooIdField.setEditable(false);
        StuIdField.setEditable(false);

        // room table
        colRoomDId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colTypeRoomId.setCellValueFactory(new PropertyValueFactory<>("type"));
        colRoomMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colRoomQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        txtSearchRoom.textProperty().addListener((observable, oldValue, newValue) -> {
            loadRoomTable(newValue);
        });

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                rooIdField.setText(newValue.getRoom_type_id());
                roomDTO = newValue;
            }
        });

        // student table
        colSId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("name"));

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                StuIdField.setText(newValue.getId());
                studentDTO = newValue;
            }
        });

        txtSearchStu.textProperty().addListener((observable, oldValue, newValue) -> {
            loadStudentTable(newValue);
        });

        // reservation table
        colResId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        colResDate.setCellValueFactory(new PropertyValueFactory<>("res_date"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colRommType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStuId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStuName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colPayStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                resIdField.setText(newValue.getRes_id());
                txtSearchRoom.setText(newValue.getRoom_type_id());
                rooIdField.setText(newValue.getRoom_type_id());
                txtSearchStu.setText(newValue.getId());
                StuIdField.setText(newValue.getId());
                resDateField.setValue(LocalDate.parse(newValue.getRes_date().toString()));
            }
        });

//        txtSearchReservation.textProperty().addListener((observable, oldValue, newValue) -> {
//            loadReservationTable(newValue);
//        });

        //---Radio buttons
        FilterPayment.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
                RadioButton rb = (RadioButton) FilterPayment.getSelectedToggle();

                switch (rb.getText()) {
                    case "All":
                        loadReservationTable("");
                        break;
                    case "Paid":
                        loadReservationTable("Paid");
                        break;
                    case "Pending":
                        loadReservationTable("Pending");
                        break;
                }
            }
        });

        loadRoomTable("");
        loadStudentTable("");
        loadReservationTable("");
    }

    private void loadReservationTable(String SearchID) {
        ObservableList<CustomDTO> list = FXCollections.observableArrayList();

        ArrayList<CustomDTO> customDTOS = reservationBO.getReservationData();
        for (CustomDTO c : customDTOS) {
            if (c.getStatus().contains(SearchID) ||
                    c.getRes_id().contains(SearchID) ||
                    c.getRoom_type_id().contains(SearchID) ||
                    c.getType().contains(SearchID)) {

                CustomDTO customDTO = new CustomDTO(
                        c.getRes_id(),
                        c.getRes_date(),
                        c.getRoom_type_id(),
                        c.getType(),
                        c.getId(),
                        c.getName(),
                        c.getKey_money(),
                        c.getStatus());

                list.add(customDTO);
            }
        }
        tblReservation.setItems(list);
    }

    private void loadStudentTable(String SearchID) {
        ObservableList<StudentDTO> list = FXCollections.observableArrayList();

        ArrayList<StudentDTO> studentDTOS = reservationBO.getStudentData();
        for (StudentDTO std : studentDTOS) {
            if (std.getId().contains(SearchID) ||
                    std.getName().contains(SearchID) ||
                    std.getAddress().contains(SearchID)) {
                StudentDTO studentDTO = new StudentDTO(std.getId(),
                        std.getName(), std.getAddress(),
                        std.getContact_no(),
                        std.getDob(),
                        std.getGender());
                list.add(studentDTO);
            }
        }
        tblStudent.setItems(list);
    }

    private void loadRoomTable(String SearchID) {
        ObservableList<RoomDTO> list = FXCollections.observableArrayList();

        ArrayList<RoomDTO> roomsDTOS = reservationBO.getRoomsData();
        for (RoomDTO std : roomsDTOS) {
            if (std.getQty() > 0) {
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
        }
        tblRoom.setItems(list);
    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        newReservationPane.setDisable(true);
        reservationPane.setDisable(false);

        resIdField.clear();

        StuIdField.clear();
        rooIdField.clear();

    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            String idText = resIdField.getText();

            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setRes_id(idText);

            boolean isDeleted = reservationBO.deleteReservation(reservationDTO);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, " Deleted ! ").show();

                loadReservationTable("");
                loadRoomTable("");
                loadStudentTable("");

                newReservationPane.setDisable(true);
                reservationPane.setDisable(false);
            } else {
                new Alert(Alert.AlertType.ERROR, " Error ! ").show();
            }
        }

    }

    @FXML
    void editBtnOnAction(ActionEvent event) {
        newReservationPane.setDisable(false);
        reservationPane.setDisable(true);
        reserveBtn.setText("Update");
        deleteBtn.setDisable(false);
        allBtn.setSelected(true);

    }

    @FXML
    void newBtnOnAction(ActionEvent event) {
        String nextID = generateNextID(reservationBO.getCurrentID());
        resIdField.setText(nextID);
        txtSearchRoom.setText("");
        rooIdField.setText("");
        txtSearchStu.setText("");
        StuIdField.setText("");

        resDateField.setValue(LocalDate.now());

        newReservationPane.setDisable(false);
        reservationPane.setDisable(true);

        deleteBtn.setDisable(true);

        reserveBtn.setText("Reserve");

        allBtn.setSelected(true);

    }

    private String generateNextID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("RS0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "RS0" + id;
        }
        return "RS01";
    }

    @FXML
    void reserveBtnOnAction(ActionEvent event) {
        /* create new dto & assign UI values to it */
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setRes_id(resIdField.getText());
        reservationDTO.setRes_date(resDateField.getValue());

        RadioButton rb = (RadioButton) PaymentStatus.getSelectedToggle();
        reservationDTO.setStatus(rb.getText());

        try {
            reservationDTO.setRoom(new Room
                    (roomDTO.getRoom_type_id(),
                            roomDTO.getType(),
                            roomDTO.getKey_money(),
                            roomDTO.getQty()));

            reservationDTO.setStudent(new Student
                    (studentDTO.getId(),
                            studentDTO.getName(),
                            studentDTO.getAddress(),
                            studentDTO.getContact_no(),
                            studentDTO.getDob(),
                            studentDTO.getGender()));
        } catch (Exception ex) {
            new Alert(Alert.AlertType.WARNING, " Select / Fill Data ! ").show();
        }

        if (resDateField.getValue() != null) {
            if (reserveBtn.getText().equals("Reserve")) {
                boolean isAdded = reservationBO.addReservation(reservationDTO);
                if (isAdded) {
                    new Alert(Alert.AlertType.INFORMATION, " Added ! ").show();

                    loadReservationTable("");
                    loadRoomTable("");
                    loadStudentTable("");

                    newReservationPane.setDisable(true);
                    reservationPane.setDisable(false);

                } else {
                    new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                }
            } else {
                boolean isAdded = reservationBO.updateReservation(reservationDTO);
                if (isAdded) {
                    new Alert(Alert.AlertType.INFORMATION, " Updated ! ").show();

                    loadReservationTable("");
                    loadRoomTable("");
                    loadStudentTable("");

                    newReservationPane.setDisable(true);
                    reservationPane.setDisable(false);

                } else {
                    new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, " Wrong Date ! ").show();
        }
    }


    @FXML
    void backBtnOnAction(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);

    }
}
