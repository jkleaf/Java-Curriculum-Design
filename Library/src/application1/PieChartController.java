package application1;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.jdbccore.MySQLQuery;
import org.po.Register;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PieChartController implements Initializable{

	@FXML PieChart pieChart;
	private List<Object> listBean=new ArrayList<>();
	
//	@Override
//	public void start(Stage primaryStage){
//		try {
//			AnchorPane root = FXMLLoader.load(getClass().
//					getResource("PieChart.fxml"));
//			Scene scene = new Scene(root,500,500);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public static void main(String[] args) {
//		launch(args);
//	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}	

	@SuppressWarnings("unchecked")
	public void buttonClicked(ActionEvent event){
//		listBean=new MySQLQuery().queryRows("select r_gender from register", Register.class, null);
//		for (Object object : listBean) {
//			System.out.println(((Register)object).getR_gender());
//		}
		ObservableList<Data> list=FXCollections.observableArrayList(
//				new PieChart.Data("Java", 40),
//				new PieChart.Data("C++", 15),
//				new PieChart.Data("C", 5),
//				new PieChart.Data("Python", 20),
//				new PieChart.Data("Php", 15),		
//				new PieChart.Data("C#", 5)
				new PieChart.Data("Male", new MySQLQuery().queryNumber
						("select count(*) from users where u_sex=?", 
								new Object[]{"f"}).doubleValue()),
				new PieChart.Data("Female", new MySQLQuery().queryNumber
						("select count(*) from users where u_sex=?", 
								new Object[]{"m"}).doubleValue())
			);
		pieChart.setData(list);
	}

}
