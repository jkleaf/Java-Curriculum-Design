package org.tools;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogDisplay {
	
	public static void showDialog(Alert alert,String title,String contentText){
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	public static void errorDialog(String title,String contentText){
		showDialog(new Alert(AlertType.ERROR), title, contentText);
	}
	
	public static void msgDialog(String title,String contentText){
		showDialog(new Alert(AlertType.INFORMATION), title, contentText);
	}
	
	public static void warningDialog(String title,String contentText){
		showDialog(new Alert(AlertType.WARNING), title, contentText);
	}
}
