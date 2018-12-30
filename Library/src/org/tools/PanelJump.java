package org.tools;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PanelJump {
		
	public void Jump2NewPanel(ActionEvent e,boolean isHide,
			String fxmlFile,int width,int height) throws IOException{
		if(isHide)
			((Node)e.getSource()).getScene().getWindow().hide();
		Stage stage=new Stage();
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlFile)));
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
