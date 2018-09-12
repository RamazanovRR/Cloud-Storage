package com.cloud.storage.client;

        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;

public class CloudStorageWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> ServerSizeViev;

    @FXML
    private Button renameOnLocal;

    @FXML
    private Button renameOnServer;

    @FXML
    private MenuItem refresh;

    @FXML
    private Button sendInServer;

    @FXML
    private TableView<?> tableVievLocal;

    @FXML
    private TableView<?> tableVievServer;

    @FXML
    private TableColumn<?, ?> ServerNameViev;

    @FXML
    private MenuItem exit;

    @FXML
    private Menu menuFile;

    @FXML
    private Menu menuHelp;

    @FXML
    private Button delOnLocal;

    @FXML
    private Button delOnServer;

    @FXML
    private Button sendInClient;

    @FXML
    private TableColumn<?, ?> localSizeViev;

    @FXML
    private TableColumn<?, ?> localNameViev;

    @FXML
    private MenuItem info;

    @FXML
    void initialize() {

    }

    public void exitAnAccount() throws Exception {
        ClientConnection.getInstance().disconnect();
        ManagerWindow.getInstance().openAuthWindow(renameOnLocal); //разобраться как передать непосредственно stage
    }
}
