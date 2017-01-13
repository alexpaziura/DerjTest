package derj.test;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private Scene scene;
	private final KeyCombination kbNext = new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN);
	private final KeyCombination kbPrev = new KeyCodeCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN);
	private final KeyCombination kbExit = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Конкурс на держслужбу");
		this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Ukraine-icon2.png")));
		initRootLayout();

		showMainView();
	}

	/**
	 * �������������� �������� �����.
	 */
	private void initRootLayout() {
		try {
			// ��������� �������� ����� �� fxml �����.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
			rootLayout = loader.load();

			// ���������� �����, ���������� �������� �����.
			scene = new Scene(rootLayout);
			RootLayoutController controller = loader.getController();
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNIFIED);
			//scene.getStylesheets().add(getClass().getResource("res/css/styles.css").toExternalForm());
			controller.setDialogStage(primaryStage);
			controller.setMainApp(this);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���������� � �������� ������ �������� �� ���������.
	 */
	private void showMainView() {
		try {
			// ��������� �������� �� ���������.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("MainView.fxml"));
			BorderPane mainV = loader.load();

			// �������� �������� �� ��������� � ����� ��������� ������.
			rootLayout.setCenter(mainV);

			MainViewController controller = loader.getController();
			scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (kbExit.match(event)) {
						primaryStage.close();				
						}
					if (kbNext.match(event)) {
						controller.btnNextClick();					
						}
					if (kbPrev.match(event)) {
						System.out.println("Prev");				
						}
				}
			});
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	boolean showGoToDialog(Tem temp) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("GoToDialog.fxml"));
	        VBox page = loader.load();
	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Перейти на");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initStyle(StageStyle.UNIFIED);
	        dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(false);
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("Ukraine-icon2.png")));
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        GoToDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        
	        controller.setData(temp);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	boolean showAboutDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("AboutDialog.fxml"));
			VBox page = loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Довідка");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.initStyle(StageStyle.UNIFIED);
			dialogStage.setResizable(false);
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("Ukraine-icon2.png")));
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			AboutDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);


			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			controller.setMainApp(this);
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
