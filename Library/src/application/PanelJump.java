package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelJump {
		
	public void Jump2NewPanel(ActionEvent e,boolean isHide,
			String fxmlFile,int width,int height) throws IOException{
		AnchorPane root = FXMLLoader.load(getClass().
				getResource(fxmlFile));
		if(isHide)
			((Node)e.getSource()).getScene().getWindow().hide();
		Stage stage=new Stage();
//		ProgressFrom progressFrom=new ProgressFrom(stage);
//		progressFrom.activateProgressBar();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//		progressFrom.cancelProgressBar();
		stage.initStyle(StageStyle.TRANSPARENT);
		DragUtil.addDragListener(stage, root);
		Scene scene = new Scene(root,width,height);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public void Jump2NewPanel(ActionEvent e,boolean isHide,
			String fxmlFile) throws IOException{
		AnchorPane root = FXMLLoader.load(getClass().
				getResource(fxmlFile));
		if(isHide)
			((Node)e.getSource()).getScene().getWindow().hide();
		Stage stage=new Stage();
//		ProgressFrom progressFrom=new ProgressFrom(stage);
//		progressFrom.activateProgressBar();
//		try {
//			Thread.sleep(1500);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//		progressFrom.cancelProgressBar();
		stage.initStyle(StageStyle.TRANSPARENT);
		DragUtil.addDragListener(stage, root);
		Scene scene = new Scene(root);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
