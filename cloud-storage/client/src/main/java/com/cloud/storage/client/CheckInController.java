package com.cloud.storage.client;

import animationObject.Shiver;
import com.cloud.storage.common.Const;
import com.cloud.storage.common.Encryption;
import com.cloud.storage.common.RegistrationPackage;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;

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
    private Group groupRadio;

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

    public void registerUser() {
        String fieldName = name.getText().trim();
        String fieldSurename = surename.getText().trim();
        String fieldLoginName = loginName.getText().trim();
        String fieldEmail = email.getText().trim();
        String fieldCity = city.getText().trim();
        String fieldGender = "";
        String fieldPass = password.getText().trim();
        String fieldRepeatPass = repeatPassword.getText().trim();
        RegistrationPackage regPack;

        if(male.isSelected()) fieldGender = "Муж";
        else fieldGender = "Жен";

        if(!fieldName.equals("") & !fieldSurename.equals("") & !fieldLoginName.equals("") & !fieldEmail.equals("")
                & !fieldCity.equals("") & !fieldPass.equals("")
                & !fieldRepeatPass.equals("") & (fieldPass.equals(fieldRepeatPass))) {
            // Registration
            regPack = new RegistrationPackage(fieldName,fieldSurename,fieldLoginName,fieldEmail,fieldCity,fieldGender,
                    Encryption.encode(fieldPass, Const.KEY));
            ClientConnection.getInstance().connection();
            Object obj = ClientConnection.getInstance().sendPackage(regPack);
            if(obj == null) System.out.println("Объект auth от сервера равен null");
            regPack = (RegistrationPackage) obj;
            if(regPack.isAuth()) {
                try {
                    ManagerWindow.getInstance().openCloudStorageWindow(registration, fieldLoginName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                shiverAllElement();
            }
        } else {
            shiverAllElement();
        }
    }

    public void shiverAllElement() {
            Shiver allNode = new Shiver(name,surename, loginName, email, city, password, repeatPassword);
    }
}
