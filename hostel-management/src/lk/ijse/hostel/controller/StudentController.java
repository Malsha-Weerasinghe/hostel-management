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
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentController {

    @FXML
    private ToggleGroup gender;

    @FXML
    private AnchorPane pane;


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

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.Type.STUDENT);

    public void initialize() {
        IdField.setEditable(false);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        makeEditableTxtField(false);
        IdField.setEditable(false);
        deleteBtn.setDisable(true);
        cancelBtn.setDisable(true);
        saveBtn.setDisable(true);
        editBtn.setDisable(true);

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
                deleteBtn.setDisable(true);
                cancelBtn.setDisable(true);
                saveBtn.setDisable(true);
                editBtn.setDisable(false);
            }
        });

//        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
//            loadStudentData(newValue);
//            makeEditableTxtField(false);
//        });

        loadStudentData("");
    }

    private void loadStudentData(String SearchID) {
        ObservableList<StudentDTO> list = FXCollections.observableArrayList();

        ArrayList<StudentDTO> studentDTOS = studentBO.getStudentData();
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

    private void makeEditableTxtField(boolean b) {
        NameField.setEditable(b);
        AddressField.setEditable(b);
        contactField.setEditable(b);
        DOBField.setEditable(b);
    }

    private void setData(StudentDTO newValue) {
        IdField.setText(newValue.getId());
        NameField.setText(newValue.getName());
        AddressField.setText(newValue.getAddress());
        contactField.setText(newValue.getContact_no());
        DOBField.setValue(LocalDate.parse(newValue.getDob()));
        if (newValue.getGender().equals("Male")) {
            maleBtn.setSelected(true);
        } else {
            femaleBtn.setSelected(true);
        }
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

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(idText);

            Boolean isAdded = studentBO.deleteStudent(studentDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, " Student Deleted ! ").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, " Error ! ").show();
            }
        }

        loadStudentData("");

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
        String nextID = generateNextID(studentBO.getCurrentID());
        IdField.setText(nextID);
        NameField.requestFocus();

    }
    private String generateNextID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("S0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "S0" + id;
        }
        return "S01";
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        if (!NameField.getText().equals("") || IdField.getText().equals("") || contactField.getText().equals("")) {
        String nameText = NameField.getText();
        String addressText = AddressField.getText();
        String contactText = contactField.getText();
        String idText = IdField.getText();
        String dobText = DOBField.getValue().toString();
        RadioButton rb = (RadioButton) gender.getSelectedToggle();
        String genderText = rb.getText();

        // regex
        if (isValidName() && isValidAddress() && isValidContact()) {
            if (saveBtn.getText().equals("Save")) {
                StudentDTO studentDTO = new StudentDTO(idText, nameText, addressText, contactText, dobText, genderText);
                Boolean isAdded = studentBO.addStudent(studentDTO);

                if (isAdded) {
                    new Alert(Alert.AlertType.INFORMATION, " Student Added ! ").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                }
            }

            if (saveBtn.getText().equals("Update")) {
                StudentDTO studentDTO = new StudentDTO(idText, nameText, addressText, contactText, dobText, genderText);
                Boolean isUpdated = studentBO.updateStudent(studentDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, " Student Updated ! ").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, " Error ! ").show();
                    clearFields();
                }
            }
            loadStudentData("");

        } else {
            new Alert(Alert.AlertType.WARNING, "Fill data !").show();
        }
    }
}

    private boolean isValidContact() {
        Pattern pattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        Matcher matcher = pattern.matcher(contactField.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(contactField);
            contactField.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidAddress() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{3,}$");
        Matcher matcher = pattern.matcher(AddressField.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(AddressField);
            AddressField.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    private boolean isValidName() {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3,}$");
        Matcher matcher = pattern.matcher(NameField.getText());

        boolean isMatches = matcher.matches();
        if (isMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(NameField);
            NameField.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    @FXML
    void backBtnOnAction(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }
    private void clearFields() {
        IdField.clear();
        NameField.clear();
        AddressField.clear();
        contactField.clear();
        DOBField.setValue(LocalDate.parse("2000-01-01"));
        maleBtn.setSelected(true);
    }

}
