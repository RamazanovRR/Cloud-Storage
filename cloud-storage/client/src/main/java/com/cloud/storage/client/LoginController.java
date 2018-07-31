package com.cloud.storage.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField textFieldLogin;

    @FXML
    private Button registration;

    @FXML
    private Button login;

    @FXML
    private PasswordField textFieldPassword;

    public void openRegistrationWindow() throws IOException {
        ManagerWindow.getInstance().openRegistrationWindow(registration);
    }
}