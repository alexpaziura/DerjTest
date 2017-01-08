package derj.test.code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GoToDialogController implements Initializable {

	@FXML
	private ComboBox cbRoz;
	@FXML
	private TextField tfQuest;
	@FXML
	private Button btnAccept;
	@FXML
	private Button btnCancel;

	private Stage dialogStage;
	private boolean okClicked = false;
	private Tem temp;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public void setData(Tem temp) {
		this.temp = temp;
		ObservableList<String> choices = FXCollections.observableArrayList();
		choices.add("Конституція України");
		choices.add("ЗУ Про держаавну службу");
		choices.add("ЗУ Про запобігання корупції");
		choices.add("Інші ЗУ");
		cbRoz.setItems(choices);
		int maxLength = 3;
		tfQuest.textProperty().addListener((observable, oldValue, newValue) -> {
			btnAccept.setDisable(newValue.trim().isEmpty());
			if (tfQuest.getText().length() > maxLength) {
				String s = tfQuest.getText().substring(0, maxLength);
				tfQuest.setText(s);
			}
			if (!newValue.matches("\\d*")) {
				tfQuest.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			temp.setRoz(cbRoz.getSelectionModel().getSelectedIndex() + 1);
			temp.setPit(Integer.parseInt(tfQuest.getText()));
			System.out.println("cbRoz " + cbRoz.getSelectionModel().getSelectedIndex());
			System.out.println("tfQ " + tfQuest.getText());
			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (tfQuest.getText() == null || tfQuest.getText().length() == 0) {
			errorMessage += "Не введено номер питання!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(tfQuest.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Номер питання має бути число!\n";
			}
		}

		if (cbRoz.getSelectionModel().isEmpty()) {
			errorMessage += "Не вибрано розділ!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Некоректне значення");
			alert.setHeaderText("Будь ласка, виправте некоректне значення!");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	InnerShadow is = new InnerShadow();

	@FXML
	private void clickAccept() {
		btnAccept.setEffect(is);
	}

	@FXML
	private void offclickAccept() {
		btnAccept.setEffect(null);
	}

	@FXML
	private void clickCancel() {
		btnCancel.setEffect(is);
	}

	@FXML
	private void offclickCancel() {
		btnCancel.setEffect(null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
