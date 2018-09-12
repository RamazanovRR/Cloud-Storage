package com.cloud.storage.client;

import animationObject.Shiver;
import com.cloud.storage.common.AuthPackage;
import com.cloud.storage.common.Encryption;
import com.cloud.storage.common.Const;
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
        AuthPackage auth;

        if(!fieldLogin.equals("") & !fieldPass.equals("")) {
            textFieldLogin.setText("");
            textFieldPassword.setText("");

            auth = new AuthPackage(fieldLogin, Encryption.encode(fieldPass, Const.KEY));
            ClientConnection.getInstance().connection();
            Object obj = ClientConnection.getInstance().sendPackage(auth);
            if(obj == null) System.out.println("Объект auth от сервера равен null");
            auth = (AuthPackage) obj;
            if(auth.isAuth()) {
                ClientConnection.getInstance().setLogin(fieldLogin);
                try {
                    ManagerWindow.getInstance().openCloudStorageWindow(login, fieldLogin);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Shiver fieldLog = new Shiver(textFieldLogin);
                Shiver fieldPas = new Shiver(textFieldPassword);
            }

        } else {
            Shiver fieldLog = new Shiver(textFieldLogin);
            Shiver fieldPas = new Shiver(textFieldPassword);
        }
    }
}