package derj.test;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutDialogController implements Initializable {


    @FXML
    private Button btnVk;
    @FXML
    private Button btnFacebook;
    @FXML
    private Button btnTwitter;
    @FXML
    private Button btnGithub;
    @FXML
    private Button btnGoogle;
    @FXML
    private Button btnOk;
    @FXML
    private ImageView imgApp;


    private Stage dialogStage;
    private boolean okClicked = false;
    private MainApp mainApp;
    private InnerShadow is = new InnerShadow();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
    boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
            okClicked = true;
            dialogStage.close();
    }
    @FXML
    private void clickOk() {
        btnOk.setEffect(is);
    }

    @FXML
    private void offclickOk() {
        btnOk.setEffect(null);
    }

    @FXML
    private void handleVk(){
        try {
            Desktop.getDesktop().browse(new URI("https://vk.com/oleksiipaziura"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clickVk() {
        btnVk.setEffect(is);
    }

    @FXML
    private void offclickVk() {
        btnVk.setEffect(null);
    }

    @FXML
    private void handleFacebook(){
        try {
            Desktop.getDesktop().browse(new URI("https://www.facebook.com/oleksii.paziura"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void clickFacebook() {
        btnFacebook.setEffect(is);
    }

    @FXML
    private void offclickFacebook() {
        btnFacebook.setEffect(null);
    }

    @FXML
    private void handleTwitter(){
        try {
            Desktop.getDesktop().browse(new URI("https://twitter.com/opaziura"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void clickTwitter() {
        btnTwitter.setEffect(is);
    }

    @FXML
    private void offclickTwitter() {
        btnTwitter.setEffect(null);
    }

    @FXML
    private void handleGoogle(){
        try {
            Desktop.getDesktop().browse(new URI("https://plus.google.com/u/0/102260010685617988442"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void clickGoogle() {
        btnGoogle.setEffect(is);
    }

    @FXML
    private void offclickGoogle() {
        btnGoogle.setEffect(null);
    }

    @FXML
    private void handleGithub(){
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/alexpaziura"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void clickGithub() {
        btnGithub.setEffect(is);
    }

    @FXML
    private void offclickGithub() {
        btnGithub.setEffect(null);
    }

}
