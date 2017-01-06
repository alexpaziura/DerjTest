package derj.test.code;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by o_paziura on 06.01.2017.
 */
public class RootLayoutController implements Initializable {

    @FXML
    private MenuItem miClose;
    @FXML
    private MenuItem miAbout;

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

    }
}
