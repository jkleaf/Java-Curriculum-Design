package application1;

import java.io.IOException;

import application.DragUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelJump {
		
	public void Jump2NewPanel(ActionEvent e,boolean isHide,
			String fxmlFile) throws IOException{
		if(isHide)
			((Node)e.getSource()).getScene().getWindow().hide();
		AnchorPane root = FXMLLoader.load(getClass().
				getResource(fxmlFile));
		Stage stage=new Stage();
		stage.initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(root);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		DragUtil.addDragListener(stage, root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void Jump2NewPanel(ActionEvent e,boolean isHide,
			String fxmlFile,int width,int height) throws IOException{
		if(isHide)
			((Node)e.getSource()).getScene().getWindow().hide();
		AnchorPane root = FXMLLoader.load(getClass().
				getResource(fxmlFile));
		Stage stage=new Stage();
		stage.initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(root,width,height);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		DragUtil.addDragListener(stage, root);
		stage.setScene(scene);
		stage.show();
	}
}
