package application;
	
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.core.MySQLQuery;
import org.po.Book;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class MainPanel extends Application implements EventHandler<MouseEvent>,Initializable{
	@FXML Button searchButton;
	@FXML Button pieChartButton;
	@FXML private TableView<Book> table;
	@FXML private TableColumn<Book,String> b_id;//=new TableColumn<>();
	@FXML private TableColumn<Book,String> b_name;//=new TableColumn<>();
	@FXML private TableColumn<Book,String> pub;//=new TableColumn<>();
	@FXML private TableColumn<Book,Date> p_date;//=new TableColumn<>();
	@FXML private TableColumn<Book,Integer> b_type;//=new TableColumn<>();
	
	private List<Book> listBean=null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		List<Object> list1=new MySQLQuery().queryRows("select *from emp", Emp.class, null);
	}
	
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
//			System.out.println(book.getB_id()+" "+book.getB_name()+" "+book.getPub()+" "+book.getP_date()+" "+book.getB_type());
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
}
