package application;

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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}	

	@SuppressWarnings("unchecked")
	public void buttonClicked(ActionEvent event){
		ObservableList<Data> list=FXCollections.observableArrayList(
				new PieChart.Data("ÄÐ", new MySQLQuery().queryNumber
						("select count(*) from users where u_sex=?", 
								new Object[]{"ÄÐ"}).doubleValue()),
				new PieChart.Data("Å®", new MySQLQuery().queryNumber
						("select count(*) from users where u_sex=?", 
								new Object[]{"Å®"}).doubleValue())
			);
		pieChart.setData(list);
	}

}
