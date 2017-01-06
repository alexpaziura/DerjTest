package derj.test.code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

	@FXML
	private ToggleButton btnAll;
	@FXML
	private ToggleButton btnFirst;
	@FXML
	private ToggleButton btnTwo;
	@FXML
	private ToggleButton btnThree;
	@FXML
	private ToggleButton btnFour;
	@FXML
	private ToggleButton btnMix;
	@FXML
	private Button btnPrev;
	@FXML
	private Button btnNext;
	@FXML
	private Button btnGo;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnEditApply;
	@FXML
	private Button btnSearch;
	@FXML
	private Label LNquestion;
	@FXML
	private TextArea taAnswer;
	@FXML
	private TextArea taQuest;
	@FXML
	private ListView<Pitannya> listView;
	@FXML
	private TextField searchF;
	@FXML
	private TabPane tabpane;

	private ObservableList<Pitannya> pitObservableList;

	private MainApp mainApp;

	public int chooseKategory;
	public boolean isMix;
	public int ih = 0;

	Connection connection = null;
	ResultSet rs;
	Statement statement;

	ArrayList<ItemHistory> history = new ArrayList<ItemHistory>();

	public int nom_pit = 1;
	public int rozdil = 1;
	public int maxNom = 100;
	int goRoz, goVop;
	InnerShadow is = new InnerShadow();

	public MainViewController() {
		pitObservableList = FXCollections.observableArrayList();
		chooseKategory = 0;
		isMix = false;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:bd/question.sqlite");
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
		} catch (Exception e) {
			System.err.println(e);
		}
		saveToHistory();
		loadQuestion();
		searchF.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					handleBtnSearch();
				}
			}
		});

		Tooltip tl1 = new Tooltip();
		tl1.setText("Всі питання");
		btnAll.setTooltip(tl1);
		Tooltip tl2 = new Tooltip();
		tl2.setText("Конституція України");
		btnFirst.setTooltip(tl2);
		Tooltip tl3 = new Tooltip();
		tl3.setText("ЗУ Про державну службу");
		btnTwo.setTooltip(tl3);
		Tooltip tl4 = new Tooltip();
		tl4.setText("ЗУ Про запобігання корупції");
		btnThree.setTooltip(tl4);
		Tooltip tl5 = new Tooltip();
		tl5.setText("Всі інші");
		btnFour.setTooltip(tl5);
		Tooltip tl6 = new Tooltip();
		tl6.setText("Змішати");
		btnMix.setTooltip(tl6);
		Tooltip tl7 = new Tooltip();
		tl7.setText("Попереднє");
		btnPrev.setTooltip(tl7);
		Tooltip tl8 = new Tooltip();
		tl8.setText("Наступне");
		btnNext.setTooltip(tl8);
		Tooltip tl9 = new Tooltip();
		tl9.setText("Перейти на");
		btnGo.setTooltip(tl9);
		Tooltip tl10 = new Tooltip();
		tl10.setText("Пошук");
		btnSearch.setTooltip(tl10);
        Tooltip tl11 = new Tooltip();
        tl11.setText("Редагувати");
        btnEdit.setTooltip(tl11);
        Tooltip tl12 = new Tooltip();
        tl12.setText("Застосувати");
        btnEditApply.setTooltip(tl12);
		btnEditApply.setVisible(false);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}

	@FXML
	private void handleBtnAll() {
		if (btnAll.isSelected()) {
			btnAll.setEffect(is);
			btnFirst.setEffect(null);
			btnTwo.setEffect(null);
			btnThree.setEffect(null);
			btnFour.setEffect(null);
			chooseKategory = 0;
			maxNom = 100;
			System.out.println(chooseKategory);
		} else {
			if ((!btnFirst.isSelected()) && (!btnTwo.isSelected()) && (!btnThree.isSelected())
					&& (!btnFour.isSelected())) {
				btnAll.setSelected(true);
			}
		}
	}

	@FXML
	private void handleBtnFirst() {
		if (btnFirst.isSelected()) {
			btnFirst.setEffect(is);
			btnAll.setEffect(null);
			btnTwo.setEffect(null);
			btnThree.setEffect(null);
			btnFour.setEffect(null);
			chooseKategory = 1;
			rozdil = 1;
			nom_pit = 1;
            loadQuestion();
			maxNom = 100;
			System.out.println(chooseKategory);
		} else {
			if ((!btnAll.isSelected()) && (!btnTwo.isSelected()) && (!btnThree.isSelected())
					&& (!btnFour.isSelected())) {
				btnFirst.setSelected(true);
			}
		}
	}

	@FXML
	private void handleBtnTwo() {
		if (btnTwo.isSelected()) {
			btnTwo.setEffect(is);
			btnAll.setEffect(null);
			btnFirst.setEffect(null);
			btnThree.setEffect(null);
			btnFour.setEffect(null);
			chooseKategory = 2;
			rozdil = 2;
			nom_pit = 1;
			maxNom = 100;
            loadQuestion();
			System.out.println(chooseKategory);
		} else {
			if ((!btnAll.isSelected()) && (!btnFirst.isSelected()) && (!btnThree.isSelected())
					&& (!btnFour.isSelected())) {
				btnTwo.setSelected(true);
			}
		}
	}

	@FXML
	private void handleBtnThree() {
		if (btnThree.isSelected()) {
			btnThree.setEffect(is);
			btnAll.setEffect(null);
			btnFirst.setEffect(null);
			btnTwo.setEffect(null);
			btnFour.setEffect(null);
			chooseKategory = 3;
			rozdil = 3;
			nom_pit = 1;
			maxNom = 100;
            loadQuestion();
			System.out.println(chooseKategory);
		} else {
			if ((!btnAll.isSelected()) && (!btnFirst.isSelected()) && (!btnTwo.isSelected())
					&& (!btnFour.isSelected())) {
				btnThree.setSelected(true);
			}
		}
	}

	@FXML
	private void handleBtnFour() {
		if (btnFour.isSelected()) {
			btnFour.setEffect(is);
			btnAll.setEffect(null);
			btnFirst.setEffect(null);
			btnTwo.setEffect(null);
			btnThree.setEffect(null);
			chooseKategory = 4;
			rozdil = 4;
			nom_pit = 1;
            loadQuestion();
			maxNom = 101;
			System.out.println(chooseKategory);
		} else {
			if ((!btnAll.isSelected()) && (!btnFirst.isSelected()) && (!btnTwo.isSelected())
					&& (!btnThree.isSelected())) {
				btnFour.setSelected(true);
			}
		}
	}

	@FXML
	private void handleBtnMix() {
		if (btnMix.isSelected()) {
			isMix = true;
			btnMix.setEffect(is);

		} else {
			isMix = false;
			btnMix.setEffect(null);
		}
		System.out.println(isMix);
	}

	@FXML
	private void btnPrevClick() {
		if (ih < history.size()) {
			nom_pit = history.get(ih).getNomPit();
			rozdil = history.get(ih).getNrozdil();
			ih++;
			loadQuestion();
		}
	}

	@FXML
	public void btnNextClick() {
		if (!isMix) {
			if (chooseKategory != 0) {
				nom_pit++;
				if (nom_pit > maxNom) {
					nom_pit = 1;
				}
			} else {
				nom_pit++;
				if (nom_pit > maxNom) {
					rozdil++;
					nom_pit = 1;
				}
			}
		} else {
			Random rand = new Random();
			if (chooseKategory == 0) {
				rozdil = rand.nextInt(3) + 1;
			}
			nom_pit = rand.nextInt(8) + 1;
		}
		System.out.println(nom_pit);
		saveToHistory();
		loadQuestion();
	}

	@FXML
	public void handleBtnSearch() {
		if (searchF.getText().equals("")) {
			return;
		}
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount() == 2) {
					Pitannya tmp = listView.getSelectionModel().getSelectedItem();
					rozdil = tmp.getRozId();
					nom_pit = tmp.getNomPit();
					chTab();
					saveToHistory();
					loadQuestion();
				}
			}
		});
		listView.getItems().clear();

		String sq = searchF.getText();
		int c = 0;
		try {

			rs = statement.executeQuery("SELECT COUNT(*) AS count FROM test WHERE pitanya LIKE '%" + sq + "%';");
			while (rs.next()) {
				c = rs.getInt("count");
			}
			if (c != 0) {
				rs = statement.executeQuery("SELECT * FROM test WHERE pitanya LIKE '%" + sq + "%';");
				int i = 1;
				while (rs.next()) {
					pitObservableList.add(new Pitannya(i, rs.getInt("rozdil"), rs.getInt("nom_pitanya"),
							rs.getString("pitanya"), rs.getString("vidpovid")));
					i++;
				}
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Конкурс на держслужбу");
				alert.setHeaderText(null);
				alert.setContentText("Нічого не знайдено!");
				alert.showAndWait();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		listView.setItems(pitObservableList);
		listView.setCellFactory(pitListView -> new PitListViewCell());
	}

	@FXML
	private void handleBtnGo() {
		Tem temp = new Tem();
		boolean okClicked = mainApp.showGoToDialog(temp);
		if (okClicked) {
			System.out.println("roz=" + temp.getRoz() + " vop=" + temp.getPit());
            rozdil = temp.getRoz();
            nom_pit = temp.getPit();
		}
		chTab();
		saveToHistory();
		loadQuestion();
	}

	@FXML
    private void handleBtnEdit() {
		boolean okClicked = mainApp.showAboutDialog();
        btnEdit.setDisable(true);
        btnEditApply.setVisible(true);
        taQuest.setEditable(true);
        taAnswer.setEditable(true);
    }

    @FXML
    private void handleBtnEditApply(){
        try {
            statement.executeUpdate("UPDATE test SET pitanya='"+taQuest.getText()+"', vidpovid='"+taAnswer.getText()+"' "+
            "WHERE rozdil=" + rozdil + " and nom_pitanya=" + nom_pit+";");
            loadQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            btnEdit.setDisable(false);
            btnEditApply.setVisible(false);
            taQuest.setEditable(false);
            taAnswer.setEditable(false);
        }
    }

	private void chTab() {
		tabpane.getSelectionModel().select(0);
	}

	private void loadQuestion() {
		try {
			rs = statement.executeQuery("select * from test where rozdil=" + rozdil + " and nom_pitanya=" + nom_pit);
			while (rs.next()) {
				LNquestion.setText("Питання №" + rs.getInt("nom_pitanya") + ":");
				taQuest.setText(rs.getString("pitanya"));
				taAnswer.setText(rs.getString("vidpovid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void saveToHistory() {
		ItemHistory tmp = new ItemHistory();
		tmp.setNomPit(nom_pit);
		tmp.setNrozdil(rozdil);
		history.add(0, tmp);
	}

	@FXML
	private void click1() {
		if (!btnFirst.isSelected()) {
			btnFirst.setEffect(is);
		}
	}

	@FXML
	private void offclick1() {
		if (!btnFirst.isSelected()) {
			btnFirst.setEffect(null);
		}
	}

	@FXML
	private void click0() {
		if (!btnAll.isSelected()) {
			btnAll.setEffect(is);
		}
	}

	@FXML
	private void offclick0() {
		if (!btnAll.isSelected()) {
			btnAll.setEffect(null);
		}
	}

	@FXML
	private void click2() {
		if (!btnTwo.isSelected()) {
			btnTwo.setEffect(is);
		}
	}

	@FXML
	private void offclick2() {
		if (!btnTwo.isSelected()) {
			btnTwo.setEffect(null);
		}
	}

	@FXML
	private void click3() {
		if (!btnThree.isSelected()) {
			btnThree.setEffect(is);
		}
	}

	@FXML
	private void offclick3() {
		if (!btnThree.isSelected()) {
			btnThree.setEffect(null);
		}
	}

	@FXML
	private void click4() {
		if (!btnFour.isSelected()) {
			btnFour.setEffect(is);
		}
	}

	@FXML
	private void offclick4() {
		if (!btnFour.isSelected()) {
			btnFour.setEffect(null);
		}
	}

	@FXML
	private void clickMix() {
		if (!btnMix.isSelected()) {
			btnMix.setEffect(is);
		}
	}

	@FXML
	private void offclickMix() {
		if (!btnMix.isSelected()) {
			btnMix.setEffect(null);
		}
	}

	@FXML
	private void clickGo() {
		btnGo.setEffect(is);
	}

	@FXML
	private void offclickGo() {
		btnGo.setEffect(null);
	}

	@FXML
	private void clickPrev() {
		btnPrev.setEffect(is);
	}

	@FXML
	private void offclickPrev() {
		btnPrev.setEffect(null);
	}

	@FXML
	private void clickNext() {
		btnNext.setEffect(is);
	}

	@FXML
	private void offclickNext() {
		btnNext.setEffect(null);
	}

	@FXML
	private void clickSearch() {
		btnSearch.setEffect(is);
	}

	@FXML
	private void offclickSearch() {
		btnSearch.setEffect(null);
	}

    @FXML
    private void clickEdit() {
        btnEdit.setEffect(is);
    }

    @FXML
    private void offclickEdit() {
        btnEdit.setEffect(null);
    }
    @FXML
    private void clickEditApply() {
        btnEditApply.setEffect(is);
    }

    @FXML
    private void offclickEditApply() {
        btnEditApply.setEffect(null);
    }
}
