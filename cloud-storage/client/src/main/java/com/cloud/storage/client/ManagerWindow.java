package com.cloud.storage.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerWindow {
    private static ManagerWindow instance;

    private ManagerWindow() {

    }

    static ManagerWindow getInstance() {
        if(instance == null) {
            instance = new ManagerWindow();
        }
        return instance;
    }
    void openRegistrationWindow(Button button) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/registrationWindow.fxml"));
        Parent root = loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    void openAuthWindow(Button button) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginWindow.fxml"));
        Parent root = loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Авторизация");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    void openCloudStorageWindow(Button button, String loginName) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cloudStorageWindow.fxml"));
        Parent root = loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("RRR-Box: " + loginName);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
