package application1;
	
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import org.core.MySQLQuery;
import org.po.Book;
import org.tools.DialogDisplay;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class MainPanel extends Application implements EventHandler<MouseEvent>,Initializable{
	@FXML Button searchButton;
	@FXML Button pieChartButton;
	@FXML Button signOutButton;
	@FXML Button clearTableButton;
	
	@FXML private TableView<Book> table;
	@FXML private TableColumn<Book,String> b_id;//=new TableColumn<>();
	@FXML private TableColumn<Book,String> b_name;//=new TableColumn<>();
	@FXML private TableColumn<Book,String> pub;//=new TableColumn<>();
	@FXML private TableColumn<Book,Date> p_date;//=new TableColumn<>();
	@FXML private TableColumn<Book,Integer> b_type;//=new TableColumn<>();
	
	@FXML private TextField textId;//public static
	@FXML private TextField textName;
	@FXML private TextField textPub;
	@FXML private TextField textDate;
	@FXML private TextField textType;
	
	@FXML private TextArea bookDsc;
	
	private List<Book> listBean=null;
	
	ObservableList<Book> bookSelected;//
			
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		List<Object> list1=new MySQLQuery().queryRows("select *from emp", Emp.class, null);		
		table.getSelectionModel().getSelectedItems().addListener(listener);
	}
	
	ListChangeListener<Book> listener=new ListChangeListener<Book>(){
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends Book> c) {
			if(table!=null){
				bookSelected=table.getSelectionModel().getSelectedItems();
				if(bookSelected!=null&&bookDsc!=null)
					bookDsc.setText(bookSelected.get(0).getContent());
			}	
//			System.out.println(bookSelected.get(0).getContent());	
		}
		
	};
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			AnchorPane root1 = FXMLLoader.load(getClass().getResource("the b.fxml"));
			Scene scene1 = new Scene(root1,935,565);
			scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene1);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
	  
	
	@SuppressWarnings("unchecked")
	public void addSearchButtonClicked(ActionEvent e){
//		Number count=new MySQLQuery().queryNumber("select count(*) from emp", null);
//		System.out.println(count);
		listBean=new MySQLQuery().queryRows("select *from book", Book.class, null);
		ObservableList<Book> list=FXCollections.observableArrayList(
				listBean);
//		for (Book book : list) {
//			System.out.println(book.getB_id()+" "+book.getB_name()+" "+book.getPub()+" "+book.getB_date()+" "+book.getB_type());
//		}
		b_id.setCellValueFactory(new PropertyValueFactory<>("b_id"));
		b_name.setCellValueFactory(new PropertyValueFactory<>("b_name"));
		pub.setCellValueFactory(new PropertyValueFactory<>("pub"));
		p_date.setCellValueFactory(new PropertyValueFactory<>("p_date"));
		b_type.setCellValueFactory(new PropertyValueFactory<>("b_type"));
		table.setItems(list);
	}
	
	public void loadPieChart(ActionEvent e) throws IOException{
		Stage PieChartPanel=new Stage();
		AnchorPane root = FXMLLoader.load(getClass().getResource("PieChart.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		PieChartPanel.setScene(scene);
		PieChartPanel.show();
	}
	
	public void searchButtonmouseEntered(){
		searchButton.setStyle("-fx-background-color:blue");
	}
	
	
	public void SignOutButtonClicked(ActionEvent e){
		System.exit(0);
		//new Stage();
	}
	
	public void addButton(ActionEvent event){
		String b_id=textId.getText();
		String b_name=textName.getText();
		String pub=textPub.getText();
		String b_date=textDate.getText();
		String b_type=textType.getText();
		if(b_id.equals("")&&b_name.equals("")&&pub.equals("")&&b_date.equals("")&&b_type.equals("")){
			DialogDisplay.errorDialog("错误", "添加值不能全为空!");
			return;
		}
//		if(new MySQLQuery().queryRows("select b_id from book", Book.class, null).contains(b_id)){
//			DialogDisplay.errorDialog("错误", "主键不能重复!");
//			return;
//		}
		Book book=new Book();
		book.setB_id(b_id);
		book.setB_name(b_name);
		book.setPub(pub);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		try {	
			book.setP_date(new java.sql.Date(formatter.parse(b_date,pos).getTime()));
		} catch (Exception e) {
//			System.out.println("错误:输入日期非法!");
			DialogDisplay.errorDialog("错误", "输入日期非法!");
			return;
		}
		book.setB_type(Integer.valueOf(b_type));
		table.getItems().add(book);
		textId.clear();
		textName.clear();
		textPub.clear();
		textDate.clear();
		textType.clear();
		new MySQLQuery().insert(book);
	}
	
	public void deleteButton(ActionEvent event){
		ObservableList<Book> bookSelected,allBooks;
		allBooks=table.getItems();
		bookSelected=table.getSelectionModel().getSelectedItems();
		if(allBooks.isEmpty()){
			DialogDisplay.errorDialog("错误", "表中没有记录，不能删除!");
			return;
		}	
		else if(bookSelected.isEmpty()){
			DialogDisplay.errorDialog("错误", "没有选中任何一行，不能删除!");
			return;
		}
//		bookDsc.setText(bookSelected.get(0).getContent());
		new MySQLQuery().delete(bookSelected.get(0));
		bookSelected.forEach(allBooks::remove);//
	}
	
	public void updateButton(){

	}
	
	public void clearTable(){
		table.getItems().clear();
		bookDsc.setText("");
	}
}
