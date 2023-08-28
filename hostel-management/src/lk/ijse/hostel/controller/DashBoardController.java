package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;

import java.io.IOException;

public class DashBoardController {
    @FXML
    private ImageView backBtn;

    @FXML
    private Button reserveBtn;

    @FXML
    private Button roomBtn;

    @FXML
    private ImageView settingBtn;

    @FXML
    private Button studentBtn;

    public AnchorPane pane;

    @FXML
    void backBtnOnAction(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);

    }

    @FXML
    void reserveBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.RESERVATION,pane);

    }

    @FXML
    void roomBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ROOMS,pane);

    }

    @FXML
    void settingBtnOnAction(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.CHANGE_PASSWORD,pane);

    }

    @FXML
    void studentBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.STUDENT,pane);

    }
}
