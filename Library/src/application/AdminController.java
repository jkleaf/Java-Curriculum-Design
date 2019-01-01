package application;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.core.MySQLQuery;
import org.join.BookAndTypes;
import org.po.Borrow_table;
import org.po.Register_Users;
import org.tools.DialogDisplay;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.scene.control.TableView;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ChoiceBox;

public class AdminController implements Initializable{
	@FXML
	private AnchorPane adminmenu;
//	@FXML
//	private TableView bookTableView,borrowTableView,userTableView;
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
	
	@FXML private Button genChartButton;
	@FXML private Button exportExcelButton;
	@FXML private TextField txtShowPath;
	
	@FXML private TableView<BookAndTypes> bookTableView;
	@FXML private TableColumn<BookAndTypes,String> bt_id;
	@FXML private TableColumn<BookAndTypes,String> bt_name;
	@FXML private TableColumn<BookAndTypes,String> bt_pub;
	@FXML private TableColumn<BookAndTypes,Date> bt_p_date;
	@FXML private TableColumn<BookAndTypes,Date> bt_type;
	@FXML private TableColumn<BookAndTypes,String> bt_type_name;
	@FXML private TableColumn<BookAndTypes,String> bt_content;
	
	@FXML private TableView<Register_Users> userTableView;
	@FXML private TableColumn<Register_Users,String> u_id;
	@FXML private TableColumn<Register_Users,String> u_name;
	@FXML private TableColumn<Register_Users,String> r_psw;
	@FXML private TableColumn<Register_Users,Date> u_creaeteday;
	@FXML private TableColumn<Register_Users,String> u_major;
	@FXML private TableColumn<Register_Users,String> u_sex;
	@FXML private TableColumn<Register_Users,String> u_age;
	@FXML private TableColumn<Register_Users,String> u_tel;
	@FXML private TableColumn<Register_Users,String> u_email;
	@FXML private TableColumn<Register_Users,String> u_hasb;
	
	@FXML private TableView<Borrow_table> borrowTableView;
	@FXML private TableColumn<Borrow_table,String> b_id;
	@FXML private TableColumn<Borrow_table,String> btu_id;
	@FXML private TableColumn<Borrow_table,Date> borrow_date;
	@FXML private TableColumn<Borrow_table,String> return_date;
	
	private String adID=menuController.adminID;
	private int selectedIndex;
	
	private List<BookAndTypes> listBean1;
	private List<Register_Users> listBean2;
	private List<Borrow_table> listBean3;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		adminID.setText(adID);
		adminNAME.setText((String)new MySQLQuery().queryValue("select name from admin"
				+" where id=?", new Object[]{adID}));
		
	}
	
	public void choiceboxini() {
		tableViewChoiceBox.setItems(FXCollections.observableArrayList("图书信息", "用户信息","借阅信息"));
//		tableViewChoiceBox.getSelectionModel().select(0);
		tableViewChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
              //System.out.println(new_value);
          	  int X=(int)(new_value);
          	  switch(X){
          		  case 0:bookTableView.setVisible(true);userTableView.setVisible(false);borrowTableView.setVisible(false);selectedIndex=0;break;
          		  case 1:bookTableView.setVisible(false);userTableView.setVisible(true);borrowTableView.setVisible(false);selectedIndex=1;break;
          		  case 2:bookTableView.setVisible(false);userTableView.setVisible(false);borrowTableView.setVisible(true);selectedIndex=2;break;
          	  }
			}
          });


		
	}

	
	public void buttonEvent(MouseEvent event) {
		if(event.getSource()==dropOut) {
			System.exit(0);
		}
			
	}
	public void searchButtonClicked(ActionEvent event){
		switch (selectedIndex) {
		case 0:
			
			break;
		case 1:
			
			break;
		case 2:
	
			break;
		}
	}
	
	public void addBookButtonClicked(ActionEvent event){
		
	}
	
	public void addContentClicked(ActionEvent event){
		FileChooser fc=new FileChooser();
		File selectedFile=fc.showOpenDialog(null);
		if(selectedFile != null){
			txtShowPath.setText(selectedFile.getAbsolutePath());
		}else{
			DialogDisplay.errorDialog("错误", "没有选择文件!");
		}
	}

	public void delBookButtonClicked(ActionEvent event){
		
	}
	
	public void delUserButtonClicked(ActionEvent event){
		
	}
	
	public void updateUserButtonClicked(ActionEvent event){
		
	}
	
	public void genChartButtonClicked(ActionEvent event){
		
	}
	
	public void exportExcelButtonClicked(ActionEvent event){
		
	}
	
	public void exitButtonClicked(ActionEvent event) {
		System.exit(0);
	}
	
}
