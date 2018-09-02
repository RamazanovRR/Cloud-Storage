package com.cloud.storage.client;

import animationObject.Shiver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Button registration;

    @FXML
    private Button login;

    public void openRegistrationWindow() throws IOException {
        ManagerWindow.getInstance().openRegistrationWindow(registration);
    }

    public void loginIn() {
        String fieldLogin = textFieldLogin.getText().trim();
        String fieldPass = textFieldPassword.getText().trim();

        if(!fieldLogin.equals("") && !fieldPass.equals("")) {
            // метод авторизации пользователя.
        } else {
            Shiver fieldLog = new Shiver(textFieldLogin);
            Shiver fieldPas = new Shiver(textFieldPassword);
            fieldLog.startAnimation();
            fieldPas.startAnimation();
        }
    }
}