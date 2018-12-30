package org.tools;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogDisplay {
	
	public static void errorDialog(String title,String contentText){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
}
