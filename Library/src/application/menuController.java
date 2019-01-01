package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.core.MySQLQuery;
import org.po.Book;
import org.po.Borrow_table;
import org.po.Register;
import org.tools.DialogDisplay;
import org.tools.Export_excel;
import org.tools.Greeting;
import org.tools.HandleDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import jxl.write.WriteException;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

public class menuController implements Initializable{
	@FXML
	private AnchorPane mid;
	@FXML
	private TextField text1;
	@FXML
	private Button searchButton;
	@FXML
	private ImageView musicImg;
	@FXML
	private AnchorPane main;
	@FXML
	private Button firstButton;
	@FXML
	private Button thirdButton;
	@FXML
	private Button secondButton;
	@FXML
	private Button forthButton;
	@FXML
	private Button fifthButton;
	@FXML
	private ImageView music2;
	@FXML
	private ImageView left1;
	@FXML
	private ImageView left2;
	@FXML
	private ImageView left3;
	@FXML
	private ImageView left4;
	@FXML
	private ImageView left5;
	@FXML
	private AnchorPane two_two;
	@FXML
	private Button two_two_one;
	@FXML
	private AnchorPane one_one;
	@FXML
	private AnchorPane three_three;
	@FXML
	private Button two_two_one111;
	@FXML
	private AnchorPane four_four;
	
	@FXML private TextField adId;
	@FXML private TextField adPsw;
	
	@FXML private Button cancelAdButton;
	@FXML private Button loginAdButton;
	@FXML private Button borrowButton;
	@FXML private Label timeLable;
	@FXML private Label userIdLabel;
	@FXML private Label usernameLabel;
	
	@FXML private Button exportExcel;
	@FXML private TextField textB_id;
	@FXML private TextField textB_name;
	@FXML private TextField textPub;
	@FXML private TextField textB_type;
	@FXML private TextArea textB_Content;
	@FXML private TextField txtBorName; 
	@FXML private TextField txtUserId;
	@FXML private TextField txtCurTime;
	@FXML private TextField txtUserPsw;
	@FXML private TextField txtRetBId;
	@FXML private TextField txtRetBorTime;
	@FXML private TextField txtBorDate;
	@FXML private TextField txtBorDays;
	@FXML private TextField txtBorPsw;
	
	
	@FXML private TableView<Book> table;
	@FXML private TableColumn<Book,String> b_id;
	@FXML private TableColumn<Book,String> b_name;
	@FXML private TableColumn<Book,String> pub;
	@FXML private TableColumn<Book,Date> p_date;
	@FXML private TableColumn<Book,Integer> b_type;
	
	@FXML private Button clearButton;
	@FXML private ComboBox<String> comboBox=new ComboBox<>(
			FXCollections.observableArrayList("书籍编号","书名","出版社","书籍类型"));	
	
//	@FXML private ChoiceBox<String> choiceBox=new ChoiceBox<>(
//			FXCollections.observableArrayList("书籍编号","书名","出版社","书籍类型"));
	private List<Book> listBean;
	private ObservableList<Book> bookSelected;
	
	private String userID=LoginController.userID;
	private String userName=LoginController.username;
//	private boolean isReturn,isBorrow;
	
	@FXML
	public void changestage(MouseEvent event) 
	{	
		if (event.getSource()==firstButton) {
			left1.setVisible(true);
			left2.setVisible(false);
			left3.setVisible(false);
			left4.setVisible(false);
			
			one_one.setVisible(true);
			two_two.setVisible(false);
			three_three.setVisible(false);
			four_four.setVisible(false);
		}
		if(event.getSource()==secondButton){
			left1.setVisible(false);
			left2.setVisible(true);
			left3.setVisible(false);
			left4.setVisible(false);
			
			one_one.setVisible(false);
			two_two.setVisible(true);
			three_three.setVisible(false);
			four_four.setVisible(false);
		}
		if(event.getSource()==thirdButton){
			left1.setVisible(false);
			left2.setVisible(false);
			left3.setVisible(true);
			left4.setVisible(false);
			
			one_one.setVisible(false);
			two_two.setVisible(false);
			three_three.setVisible(true);
			four_four.setVisible(false);
		}
		if(event.getSource()==forthButton){
		  	left1.setVisible(false);
			left2.setVisible(false);
			left3.setVisible(false);
			left4.setVisible(true);
			one_one.setVisible(false);
			two_two.setVisible(false);
			three_three.setVisible(false);
			four_four.setVisible(true);
		}
		if(event.getSource()==fifthButton) {
			System.exit(0);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userIdLabel.setText(userID);
		usernameLabel.setText(userName);
		textB_Content.setWrapText(true);
		timeLable.setText(Greeting.say_hi());
		table.getSelectionModel().getSelectedItems().addListener(listener);
		comboBox.getSelectionModel().select(0);
//		comboBox.getSelectionModel().selectedItemProperty().addListener(clistener);
//		choiceBox.getSelectionModel().selectedItemProperty().addListener(listener);
	}
	ListChangeListener<Book> listener=new ListChangeListener<Book>(){
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends Book> c) {
			if(table!=null){
				bookSelected=table.getSelectionModel().getSelectedItems();
//				table.setStyle("-fx-background-color:#737675");
				if(bookSelected!=null&&textB_Content!=null&&textB_id!=null
						&&textB_name!=null&&textPub!=null&&textB_type!=null){
					textB_id.setText(bookSelected.get(0).getB_id());
					textB_name.setText(bookSelected.get(0).getB_name());
					textPub.setText(bookSelected.get(0).getPub().toString());
					textB_type.setText(bookSelected.get(0).getB_type().toString());
					textB_Content.setText(bookSelected.get(0).getContent());
					txtBorName.setText(bookSelected.get(0).getB_name());
					List<Integer> list=HandleDate.getLocalTime();
					txtCurTime.setText(list.get(0)+"-"+list.get(1)+"-"+list.get(2));
					txtRetBId.setText(bookSelected.get(0).getB_id());
					txtUserId.setText(userID);
				}	
			}	
//			System.out.println(bookSelected.get(0).getContent());	
		}	
	};
	
	ChangeListener<Book> clistener=new ChangeListener<Book>() {

		@Override
		public void changed(ObservableValue<? extends Book> observable, Book oldValue, Book newValue) {
			
		}
	};
	
	
	@SuppressWarnings("unchecked")
	public void searchButtonClicked(ActionEvent event){
		listBean=new MySQLQuery().queryRows("select *from book", Book.class, null);
		ObservableList<Book> list=FXCollections.observableArrayList(
				listBean);
//		for (Book book : list) {
//			System.out.println(book.getB_id()+" "+book.getB_name()+" "+book.getPub()+" "+book.getP_date()+" "+book.getB_type());		
//		}
		b_id.setCellValueFactory(new PropertyValueFactory<>("b_id"));
		b_name.setCellValueFactory(new PropertyValueFactory<>("b_name"));
		pub.setCellValueFactory(new PropertyValueFactory<>("pub"));
		p_date.setCellValueFactory(new PropertyValueFactory<>("p_date"));
		b_type.setCellValueFactory(new PropertyValueFactory<>("b_type"));
		table.setItems(list);
	}
	
	@SuppressWarnings("unchecked")
	public void exportExcelButtonClicked(ActionEvent event){
		try {
			Export_excel.export(new MySQLQuery().queryRows("select *from book", Book.class, null), "book");
			if(!table.getItems().isEmpty())
				DialogDisplay.msgDialog("消息提示", "导出Excel表成功!");
			else
				DialogDisplay.warningDialog("警告", "当前表格为空，不可导出!");
		} catch (WriteException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clearButtonClicked(ActionEvent event){
		table.getItems().clear();
		textB_id.setText("");
		textB_name.setText("");
		textB_type.setText("");
		textPub.setText("");
		textB_Content.setText("");
		txtBorName.setText("");
		txtCurTime.setText("");
		txtUserId.setText("");
		txtUserPsw.setText("");
		txtRetBId.setText("");
		txtBorDate.setText("");
		txtBorDays.setText("");
		txtBorPsw.setText("");
	}
	
	public void playMusic(MouseEvent event){
		System.out.println("Music~~~");
	}
	
	public void adLoginButtonClicked(ActionEvent event) throws IOException{
		if(LoginModel.adminIslogin(adId.getText(), adPsw.getText())){
			System.out.println("successfully login!");
			new PanelJump().Jump2NewPanel(event, true, "admin.fxml");
		}else{
			DialogDisplay.errorDialog("错误", "用户名或密码错误!");
			System.err.println("username or password is incorrect!");			
		}
	}
	
	public void adCancelButtonClicked(ActionEvent event){
		
	}
	
	public void borrowButtonClicked(ActionEvent event){
		String psw=txtUserPsw.getText();
		String borName=txtBorName.getText();
		String curTime=txtCurTime.getText();
		if(borName.equals("")||curTime.equals("")||txtUserId.getText().equals("")){
			DialogDisplay.msgDialog("消息提示", "请选择要借阅的书本!");
			return;
		}
		if(psw.equals("")){
			DialogDisplay.msgDialog("消息提示", "请填写用户密码!");
			return;
		}else{
			if(LoginModel.checkBorrowPsw(psw)){
				DialogDisplay.msgDialog("消息提示", "借阅成功!");
				Borrow_table bt=new Borrow_table();
				bt.setB_id(textB_id.getText());
				bt.setBorrow_date(HandleDate.string2Date(curTime));
				bt.setU_id(userID);
				new MySQLQuery().insert(bt);
				txtUserPsw.setText("");
				return;
			}else{
				DialogDisplay.errorDialog("错误", "密码错误!");
				return;
			}
		}
		
	}
	
	public void returnButtonClicked(ActionEvent event){
		String psw=txtBorPsw.getText();
		if(txtRetBId.getText().equals("")||txtBorDate.equals("")||txtBorDays.equals("")){
			DialogDisplay.msgDialog("消息提示", "请选择要归还的书本!");
			return;
		}
		if(psw.equals("")){
			DialogDisplay.msgDialog("消息提示", "请填写借阅密码!");
			return;
		}else{
			if(LoginModel.checkBorrowPsw(psw)){
				@SuppressWarnings("unchecked")
				List<Borrow_table> list=new MySQLQuery().queryRows("select * from borrow_table"
						+" where u_id=?",Borrow_table.class, new Object[]{userID});
				String bId=list.get(0).getB_id();
				if(list==null||list.isEmpty()||!bId.equals(txtRetBId.getText())){
					DialogDisplay.errorDialog("错误", "您未借阅该书!");
					return;
				}else{
					String borrowDate=new MySQLQuery().queryValue("select borrow_date from borrow_table"
							+" where u_id=?", new Object[]{userID}).toString();
					txtBorDate.setText(borrowDate);
					String curDate=LocalDate.now().toString();
					txtBorDays.setText(HandleDate.getDuration(borrowDate, curDate).toString());
					DialogDisplay.msgDialog("消息提示", "归还成功!");
					new MySQLQuery().executeSQL("update borrow_table set return_date=? where u_id=?",
								new Object[]{curDate,userID});//无主键
					txtBorDate.setText("");
					txtBorDays.setText("");
					txtBorPsw.setText("");
					return;
				}
			}else{
				DialogDisplay.errorDialog("错误", "密码错误!");
				return;
			}
		}
//		if(txtRetBId.getText().equals("")||.equals("")||txtUserId.equals("")){
//			DialogDisplay.msgDialog("消息提示", "请选择要借阅的书本!");
//			return;
//		}
		
	}

}
