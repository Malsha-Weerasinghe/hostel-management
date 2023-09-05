package lk.ijse.hostel.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label ForgetPassword;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private ImageView imgPasswordView;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label shownPassword;

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private TextField userNameField;

    UserBO userBO =(UserBO) BOFactory.getBoFactory().getBO(BOFactory.Type.USER);

    @FXML
    void ForgetPasswordOnAction(MouseEvent event) {
        new Alert(Alert.AlertType.INFORMATION,"Please contact Developer !\n0704109463").show();

    }

    public void initialize(){
        shownPassword.setVisible(false);
    }

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        Shake shakeUserName = new Shake(userNameField);
        Shake shakePassword = new Shake(PasswordField);

        if( isCorrectPassword() && isCorrectUserName()){
            Navigation.navigate(Routes.DASHBOARD, pane);
            new FadeIn(pane).setSpeed(3).play();

        }else if (isCorrectPassword() && !isCorrectUserName()) {
            userNameField.requestFocus();
            userNameField.setStyle("-fx-border-color: red");
            shakeUserName.play();
        } else if (!isCorrectPassword() && isCorrectUserName()) {
            PasswordField.requestFocus();
            userNameField.setStyle("-fx-border-color: red");
            shakePassword.play();
        } else{
            new Alert(Alert.AlertType.ERROR,"Try again !").show();
            PasswordField.clear();
            userNameField.clear();
        }
    }

    @FXML
    void toggleButtonOnAction(ActionEvent event) {
        if (toggleButton.isSelected()) {
            shownPassword.setVisible(true);
            shownPassword.textProperty().bind(Bindings.concat(PasswordField.getText()));
            toggleButton.setText("Hide");
            imgPasswordView.setImage(new Image("resources/img/eye-close.png"));

        }else{
            shownPassword.setVisible(false);
            PasswordField.setVisible(true);
            toggleButton.setText("Show");
            imgPasswordView.setImage(new Image("resources/img/eye-open.png"));
        }

    }

    @FXML
    void passwordFieldKeyTyped(KeyEvent event) {
        shownPassword.textProperty().bind(Bindings.concat(PasswordField.getText()));

    }

    private boolean isCorrectUserName() {
        String user = userBO.getUser("1");
        if(user == null){
            new Alert(Alert.AlertType.ERROR," Database Error !").show();
            return false;
        }
        return userNameField.getText().equals(user);
    }

    private boolean isCorrectPassword() {
        String password = userBO.getPassword("1");
        if(password == null){
            new Alert(Alert.AlertType.ERROR," Database Error !").show();
            return false;
        }
        return PasswordField.getText().equals(password);
    }

}
