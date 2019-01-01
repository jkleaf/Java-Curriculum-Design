package application1;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.core.MySQLQuery;
import org.po.Book;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
	private Button enter21;
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
	private Button two_two_two;
	@FXML
	private AnchorPane one_one;
	@FXML
	private AnchorPane three_three;
	@FXML
	private Button two_two_one11;
	@FXML
	private Button two_two_one111;
	@FXML
	private AnchorPane four_four;
	@FXML
	private TextField ADname;
	@FXML
	private TextField ADname1;
	@FXML
	private Button exportExcel;
	@FXML private TextField textB_id;
	@FXML private TextField textB_name;
	@FXML private TextField textPub;
	@FXML private TextField textB_type;
	@FXML private TextArea textB_Content;
	
	@FXML private TableView<Book> table;
	@FXML private TableColumn<Book,String> b_id;
	@FXML private TableColumn<Book,String> b_name;
	@FXML private TableColumn<Book,String> pub;
	@FXML private TableColumn<Book,Date> p_date;
	@FXML private TableColumn<Book,Integer> b_type;
	
	@FXML private Button clearButton;
	@FXML private ComboBox<String> comboBox;
	
	private List<Book> listBean;
	
	ObservableList<Book> bookSelected;

	// Event Listener on Button[#one].onMouseClicked
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
		table.getSelectionModel().getSelectedItems().addListener(listener);
	}
	
	ListChangeListener<Book> listener=new ListChangeListener<Book>(){
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends Book> c) {
			if(table!=null){
				bookSelected=table.getSelectionModel().getSelectedItems();
				if(bookSelected!=null&&textB_Content!=null)
					textB_Content.setText(bookSelected.get(0).getContent());
			}	
//			System.out.println(bookSelected.get(0).getContent());	
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
	
	public void exportExcelButtonClicked(ActionEvent event){
	
	}
	
	public void clearButtonClicked(ActionEvent event){
		table.getItems().clear();
		textB_Content.setText("");
	}
	
	public void playMusic(MouseEvent event){
		System.out.println("Music~~~");
	}

}
