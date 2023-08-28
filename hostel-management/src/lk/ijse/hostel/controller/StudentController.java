package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostel.dto.StudentDTO;

public class StudentController {

    @FXML
    private TextField AddressField;

    @FXML
    private DatePicker DOBField;

    @FXML
    private TextField IdField;

    @FXML
    private TextField NameField;

    @FXML
    private Button cancelBtn;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField contactField;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;

    @FXML
    private RadioButton femaleBtn;

    @FXML
    private RadioButton maleBtn;

    @FXML
    private Button newBtn;

    @FXML
    private ImageView backBtn;

    @FXML
    private Button saveBtn;

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
    void saveBtnOnAction(ActionEvent event) {

    }

    @FXML
    void backBtnOnAction(MouseEvent event) {

    }
}
