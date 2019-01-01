package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainPanel extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		Scene scene=new Scene(root);
		primaryStage.initStyle(StageStyle.TRANSPARENT);//��ʼ�����ڷ���ޱ߿򻯣�����͸����
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		DragUtil.addDragListener(primaryStage, root);//��Ӵ����϶�������
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
