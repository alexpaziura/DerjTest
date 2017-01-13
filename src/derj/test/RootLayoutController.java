package derj.test;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RootLayoutController implements Initializable {

    @FXML
    private MenuItem miClose;
    @FXML
    private MenuItem miAbout;

    private MainApp mainApp;
    private Stage dialogStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleClose() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void handleAbout() {
        boolean okClicked = mainApp.showAboutDialog();

    }

    void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
