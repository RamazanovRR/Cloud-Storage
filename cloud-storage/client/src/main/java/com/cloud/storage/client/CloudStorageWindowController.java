package com.cloud.storage.client;

        import java.io.File;
        import java.net.URL;
        import java.util.ResourceBundle;

        import com.cloud.storage.common.Const;
        import com.cloud.storage.common.DataPackage;
        import com.cloud.storage.common.DirsAndFiles;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;

public class CloudStorageWindowController implements Const {

    private ObservableList<FileInfo> localFileInfos;
    private ObservableList<FileInfo> serverFileInfos;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<FileInfo, String> serverSizeViev;

    @FXML
    private Button renameOnLocal;

    @FXML
    private Button renameOnServer;

    @FXML
    private MenuItem refresh;

    @FXML
    private Button sendInServer;

    @FXML
    private TableView<FileInfo> tableVievLocal;

    @FXML
    private TableView<FileInfo> tableVievServer;

    @FXML
    private TableColumn<FileInfo, String> serverNameViev;

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
    private TableColumn<FileInfo, String> localSizeViev;

    @FXML
    private TableColumn<FileInfo, String> localNameViev;

    @FXML
    private MenuItem info;

    @FXML
    void initialize() {
        DataPackage datPac = new DataPackage(CREATE_DIRS, ClientConnection.getInstance().getLogin());
        DirsAndFiles.createLocalDirs(ClientConnection.getInstance().getLogin());
        ClientConnection.getInstance().sendPackage(datPac);
        datPac = new DataPackage(REFRESH_FILES, ClientConnection.getInstance().getLogin());
        datPac = (DataPackage) ClientConnection.getInstance().sendPackage(datPac);

        localFileInfos = readFileInfo(DirsAndFiles.returnLocalList(ClientConnection.getInstance().getLogin()));
        serverFileInfos = readFileInfo(datPac.getFiles());

        localNameViev.setCellValueFactory(new PropertyValueFactory<FileInfo, String>("nameFile"));
        localSizeViev.setCellValueFactory(new PropertyValueFactory<FileInfo, String>("sizeFile"));
        serverNameViev.setCellValueFactory(new PropertyValueFactory<FileInfo, String>("nameFile"));
        serverSizeViev.setCellValueFactory(new PropertyValueFactory<FileInfo, String>("sizeFile"));


        tableVievLocal.setItems(localFileInfos);
        tableVievServer.setItems(serverFileInfos);


    }

    public void exitAnAccount() throws Exception {
        ClientConnection.getInstance().disconnect();
        ManagerWindow.getInstance().openAuthWindow(renameOnLocal); //разобраться как передать непосредственно stage
    }

    public ObservableList<FileInfo> readFileInfo(File[] files) {
        ObservableList<FileInfo> fileInfos = FXCollections.observableArrayList();;
        if(files.length == 0) return fileInfos;

        for(int i = 0; i < files.length; i++) {
            fileInfos.add(new FileInfo(files[i].getName(), DirsAndFiles.weight(files[i])));
        }

        return fileInfos;
    }
}
