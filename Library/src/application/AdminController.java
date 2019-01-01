package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableView;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;

public class AdminController {
	@FXML
	private AnchorPane adminmenu;
	@FXML
	private TableView bookTableView,borrowTableView,userTableView;
	@FXML
	private TextField searchTexFiled;
	@FXML
	private Button searchButton;
	@FXML
	private ChoiceBox<Object> tableViewChoiceBox;
	@FXML
	private TextField bookPress;
	@FXML
	private TextField bookID;
	@FXML
	private TextField bookName;
	@FXML
	private TextField bookType;
	@FXML
	private DatePicker bookDataPicker;
	@FXML
	private Button updateInfoButton;
	@FXML
	private Button deletUserButton;
	@FXML
	private Button deletBookButton;
	@FXML
	private Button addBookButton;
	@FXML
	private Button addTextButton;
	@FXML
	private Button dropOut;
	@FXML
	private TextField adminID;
	@FXML
	private TextField adminNAME;
	
	public void choiceboxini() {
		tableViewChoiceBox.setItems(FXCollections.observableArrayList("图书信息", "用户信息","借阅信息"));

		tableViewChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
              //System.out.println(new_value);
          	  int X=(int)(new_value);
          	  switch(X){
          		  case 0:bookTableView.setVisible(true);userTableView.setVisible(false);borrowTableView.setVisible(false);break;
          		  case 1:bookTableView.setVisible(false);userTableView.setVisible(true);borrowTableView.setVisible(false);break;
          		  case 2:bookTableView.setVisible(false);userTableView.setVisible(false);borrowTableView.setVisible(true);break;
          	  }
			}
          });


		
	}
	
//	public void buttonEvent(MouseEvent event) {
//		if(event.getSource()==dropOut) {
//			System.exit(0);
//		}
//			
//	}
	
	
}
