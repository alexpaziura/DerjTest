package derj.test;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class PitListViewCell extends ListCell<Pitannya> {

	@FXML
	private Label nomQue;
	@FXML
	private Label nomPit;
	@FXML
	private Label rozId;
	@FXML
	private Label pitan;
	@FXML
	private HBox hbox;
	
	private FXMLLoader mLLoader;
	
	@Override
    protected void updateItem(Pitannya pitannya, boolean empty) {
        super.updateItem(pitannya, empty);

        if(empty || pitannya == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            nomQue.setText(String.valueOf(pitannya.getPitId()));
            nomPit.setText(String.valueOf(pitannya.getNomPit()));
            pitan.setText(pitannya.getPit());

            switch(pitannya.getRozId())
            {
            	case 1: rozId.setText("Конституція України");
            		break;
            	case 2: rozId.setText("ЗУ Про держслужбу");
            		break;
            	case 3: rozId.setText("ЗУ Про запобігання корупції");
            		break;
            	case 4: rozId.setText("Інші ЗУ");
            		break;
            }
                   
            setText(null);
            setGraphic(hbox);
        }

    }
}
