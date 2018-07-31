package com.cloud.storage.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class CheckInController {

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repeatPassword;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField city;

    @FXML
    private TextField loginName;

    @FXML
    private TextField name;

    @FXML
    private Button registration;

    @FXML
    private Button back;

    @FXML
    private TextField surename;

    @FXML
    private RadioButton female;

    @FXML
    private TextField email;

    @FXML
    private RadioButton male;

    public void openAuthWindow() throws IOException {
        ManagerWindow.getInstance().openAuthWindow(back);
    }
}
