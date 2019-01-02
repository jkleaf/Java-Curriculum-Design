package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.jdbccore.MySQLQuery;
import org.jdbcjoin.BookAndTypes;
import org.po.Book;
import org.po.Borrow_table;
import org.po.Register_Users;
import org.tools.DialogDisplay;
import org.tools.Export_excel;
import org.tools.HandleDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.write.WriteException;
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
	private DatePicker bookDataPicker=new DatePicker();
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
	@FXML private TableColumn<BookAndTypes,String> bat_id;
	@FXML private TableColumn<BookAndTypes,String> bat_name;
	@FXML private TableColumn<BookAndTypes,String> bat_pub;
	@FXML private TableColumn<BookAndTypes,Date> bat_p_date;
	@FXML private TableColumn<BookAndTypes,Date> bat_type;
	@FXML private TableColumn<BookAndTypes,String> bat_type_name;
	@FXML private TableColumn<BookAndTypes,String> bat_content;
	
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
	@FXML private TableColumn<Borrow_table,String> bot_id;
	@FXML private TableColumn<Borrow_table,String> botu_id;
	@FXML private TableColumn<Borrow_table,Date> borrow_date;
	@FXML private TableColumn<Borrow_table,String> return_date;
	
	private String adID=menuController.adminID;
	private int selectedIndex;
	
	private List<BookAndTypes> listBean1;
	private List<Register_Users> listBean2;
	private List<Borrow_table> listBean3;
	
	ObservableList<BookAndTypes> bookSelected;
	ObservableList<Register_Users> userSelected;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		adminID.setText(adID);
		adminNAME.setText((String)new MySQLQuery().queryValue("select name from admin"
				+" where id=?", new Object[]{adID}));
		bookTableView.getSelectionModel().getSelectedItems().addListener(
			new ListChangeListener<BookAndTypes>() {
				@Override
				public void onChanged(ListChangeListener.Change<? extends BookAndTypes> c) {
					if(bookTableView!=null){
						bookSelected=bookTableView.getSelectionModel().getSelectedItems();
					}
				}
		});
		
		userTableView.getSelectionModel().getSelectedItems().addListener(
				new ListChangeListener<Register_Users>() {
					@Override
					public void onChanged(ListChangeListener.Change<? extends Register_Users> c) {
						if(bookTableView!=null){
							userSelected=userTableView.getSelectionModel().getSelectedItems();
						}
					}
			});
		tableViewChoiceBox.setItems(FXCollections.observableArrayList("图书信息", "用户信息","借阅信息"));
//		tableViewChoiceBox.getSelectionModel().select(0);
		tableViewChoiceBox.getSelectionModel().selectedIndexProperty().addListener(cListener);
	}
	
//	public void choiceboxini() {
//		
//	}
	ChangeListener<Number> cListener=new ChangeListener<Number>() {
		public void changed(ObservableValue ov, Number value, Number new_value) {
			//System.out.println(new_value);
			int X=(int)(new_value);
			switch(X){
			case 0:bookTableView.setVisible(true);userTableView.setVisible(false);borrowTableView.setVisible(false);selectedIndex=0;break;
			case 1:bookTableView.setVisible(false);userTableView.setVisible(true);borrowTableView.setVisible(false);selectedIndex=1;break;
			case 2:bookTableView.setVisible(false);userTableView.setVisible(false);borrowTableView.setVisible(true);selectedIndex=2;break;
			}
		} 
	};
	
	public void buttonEvent(MouseEvent event) {
		if(event.getSource()==dropOut) {
			System.exit(0);
		}
			
	}
	
	@SuppressWarnings("unchecked")
	public void searchButtonClicked(ActionEvent event){
		String txtSearch=searchTexFiled.getText();
		if(searchTexFiled==null||txtSearch.equals("")){
			return;
		}
		switch (selectedIndex) {
		case 0:
			listBean1=new MySQLQuery().queryRows("select book.*,types.* from book,types where "
					+ "book.b_type=types.type and concat(b_id,b_name,pub,type_name) like  \"%\"?\"%\"", 
					BookAndTypes.class, new Object[]{txtSearch});
			if(listBean1==null||listBean1.equals("")){
				DialogDisplay.msgDialog("消息提示", "查询没有结果");
				bookTableView.getItems().clear();
				return;
			}
			ObservableList<BookAndTypes> batList=FXCollections.observableArrayList(
					listBean1);
			bat_id.setCellValueFactory(new PropertyValueFactory<>("b_id"));
			bat_name.setCellValueFactory(new PropertyValueFactory<>("b_name"));
			bat_pub.setCellValueFactory(new PropertyValueFactory<>("pub"));
			bat_p_date.setCellValueFactory(new PropertyValueFactory<>("p_date"));
			bat_type.setCellValueFactory(new PropertyValueFactory<>("type"));			
			bat_type_name.setCellValueFactory(new PropertyValueFactory<>("type_name"));
			bat_content.setCellValueFactory(new PropertyValueFactory<>("filepath"));
			bookTableView.getItems().clear();
			bookTableView.setItems(batList);
			break;
		case 1:
			listBean2=new MySQLQuery().queryRows("select register.*,users.* from register,users where "
					+ "r_id=u_id and concat(u_id,u_name,u_sex,u_major,u_tel) like  \"%\"?\"%\"", 
					Register_Users.class,new Object[]{txtSearch});
			if(listBean2==null||listBean2.equals("")){
				DialogDisplay.msgDialog("消息提示", "查询没有结果");
				userTableView.getItems().clear();
				return;
			}
			ObservableList<Register_Users> ruList=FXCollections.observableArrayList(
					listBean2);
			u_id.setCellValueFactory(new PropertyValueFactory<>("u_id"));
			u_name.setCellValueFactory(new PropertyValueFactory<>("u_name"));
			r_psw.setCellValueFactory(new PropertyValueFactory<>("r_password"));
			u_creaeteday.setCellValueFactory(new PropertyValueFactory<>("u_createday"));
			u_major.setCellValueFactory(new PropertyValueFactory<>("u_major"));
			u_sex.setCellValueFactory(new PropertyValueFactory<>("u_sex"));
			u_age.setCellValueFactory(new PropertyValueFactory<>("u_age"));
			u_tel.setCellValueFactory(new PropertyValueFactory<>("u_tel"));
			u_email.setCellValueFactory(new PropertyValueFactory<>("u_email"));
			u_hasb.setCellValueFactory(new PropertyValueFactory<>("u_hasb"));
			userTableView.getItems().clear();
			userTableView.setItems(ruList);
			break;
		case 2:
			listBean3=new MySQLQuery().queryRows("select * from borrow_table where "
					+ "concat(b_id,u_id,borrow_date,return_date) like  \"%\"?\"%\"", 
					Borrow_table.class,new Object[]{txtSearch});
			if(listBean3==null||listBean3.equals("")){
				DialogDisplay.msgDialog("消息提示", "查询没有结果");
				borrowTableView.getItems().clear();
				return;
			}
			ObservableList<Borrow_table> botList=FXCollections.observableArrayList(
					listBean3);
			bot_id.setCellValueFactory(new PropertyValueFactory<>("b_id"));
			botu_id.setCellValueFactory(new PropertyValueFactory<>("u_id"));
			borrow_date.setCellValueFactory(new PropertyValueFactory<>("borrow_table"));
			return_date.setCellValueFactory(new PropertyValueFactory<>("return_table"));
			borrowTableView.getItems().clear();
			borrowTableView.setItems(botList);
			break;
		}
	}
	
	public void addBookButtonClicked(ActionEvent event){
		String b_id=bookID.getText();
		String b_name=bookName.getText();
		String pub=bookPress.getText();
		String type_name=bookType.getText();
		if(bookDataPicker.getValue()==null){
			DialogDisplay.errorDialog("错误", "添加值不能为空!");
			return;
		}
		String p_date=bookDataPicker.getValue().toString();
		System.out.println(p_date);
		String filepath=txtShowPath.getText();
		if(b_id.equals("")||b_name.equals("")||pub.equals("")||type_name.equals("")
				||p_date.equals("")||filepath.equals("")||bookDataPicker.getValue().toString().equals("")){
			DialogDisplay.errorDialog("错误", "添加值不能为空!");
			return;
		}
//		ObservableList<Types>
//		if()
		BookAndTypes bat=new BookAndTypes();
		bat.setB_id(b_id);
		bat.setB_name(b_name);
		bat.setPub(pub);
		bat.setB_type((Integer)(new MySQLQuery().queryNumber("select type from types"
				+ " where type_name=?", new Object[]{type_name})));
		bat.setFilepath(filepath);
		bat.setP_date(HandleDate.string2Date(p_date));
		bat.setType_name(type_name);
		Book book=new Book();
		book.setB_id(b_id);
		book.setB_name(b_name);
		book.setPub(pub);
		book.setB_type((Integer)(new MySQLQuery().queryNumber("select type from types"
				+ " where type_name=?", new Object[]{type_name})));
		book.setFilepath(filepath);
		book.setP_date(HandleDate.string2Date(p_date));
		bookTableView.getItems().add(bat);
		bookID.clear();
		bookName.clear();
		bookPress.clear();
		bookType.clear();
//		bookDataPicker.clear();
		txtShowPath.clear();
		if(bookTableView.getItems().contains(b_id)){
			DialogDisplay.errorDialog("错误", "主键值为空或已重复!");
		}
		new MySQLQuery().insert(book);
		DialogDisplay.msgDialog("消息提示", "添加书本成功!");
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
		ObservableList<BookAndTypes> bookSelected,allBooks;
		allBooks=bookTableView.getItems();
		bookSelected=bookTableView.getSelectionModel().getSelectedItems();
		if(allBooks.isEmpty()){
			DialogDisplay.errorDialog("错误", "表中没有记录，不能删除!");
			return;
		}	
		else if(bookSelected.isEmpty()){
			DialogDisplay.errorDialog("错误", "没有选中任何一行，不能删除!");
			return;
		}
		new MySQLQuery().executeSQL("delete from book where b_id=?", new Object[]{bookSelected.get(0).getB_id()});
		bookSelected.forEach(allBooks::remove);
		DialogDisplay.msgDialog("消息提示", "删除成功!");
	}
	
	public void delUserButtonClicked(ActionEvent event){
		ObservableList<Register_Users> userSelected,allUsers;
		allUsers=userTableView.getItems();
		userSelected=userTableView.getSelectionModel().getSelectedItems();
		if(allUsers.isEmpty()){
			DialogDisplay.errorDialog("错误", "表中没有记录，不能删除!");
			return;
		}	
		else if(userSelected.isEmpty()){
			DialogDisplay.errorDialog("错误", "没有选中任何一行，不能删除!");
			return;
		}
		new MySQLQuery().executeSQL("delete from users where u_id=?", new Object[]{userSelected.get(0).getU_id()});
		new MySQLQuery().executeSQL("delete from register where r_id=?", new Object[]{userSelected.get(0).getR_id()});
		userSelected.forEach(allUsers::remove);
		DialogDisplay.msgDialog("消息提示", "删除成功!");
	}
	
	public void updateUserButtonClicked(ActionEvent event){
		
	}
	
	public void genChartButtonClicked(ActionEvent event) throws IOException{
		Stage PieChartPanel=new Stage();
		AnchorPane root = FXMLLoader.load(getClass().getResource("PieChart.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		PieChartPanel.setScene(scene);
		PieChartPanel.show();
	}
	
	public void exportExcelButtonClicked(ActionEvent event){
		try {
			Export_excel.export(listBean1, "books");
			if(!bookTableView.getItems().isEmpty()&&bookTableView!=null)
				DialogDisplay.msgDialog("消息提示", "导出Excel表成功!");
			else
				DialogDisplay.warningDialog("警告", "当前表格为空，不可导出!");
		} catch (WriteException | IOException e) {
			e.printStackTrace();
		}
		//switch()
		/*
		 * 
		 */
	}
	
	public void clearButtonClicked(ActionEvent event){
		switch (selectedIndex) {
		case 0:
			bookTableView.getItems().clear();
//			bookID.setText("");
//			bookName.setText("");
//			bookPress.setText("");
//			bookType.setText("");
//			bookDataPicker.setTex
//			txtShowPath.setText("");
			break;
		case 1:
			userTableView.getItems().clear();
			break;
		case 2:
			borrowTableView.getItems().clear();
			break;
		}
	}
	
	public void exitButtonClicked(ActionEvent event) {
		System.exit(0);
	}
	
}
