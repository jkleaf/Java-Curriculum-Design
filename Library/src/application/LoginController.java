package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jdbccore.MySQLQuery;
import org.tools.DialogDisplay;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController extends Application{
	@FXML Button registerButton;
	@FXML TextField textField;
	@FXML PasswordField passwordField;
	@FXML Button loginButton;
	@FXML Button exitButton;
	
	private static Stage stage;
	public static String username;
	public static String password;
	public static String userID;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage=primaryStage;
			AnchorPane root = FXMLLoader.load(getClass().
					getResource("login.fxml"));
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root);
			scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
			DragUtil.addDragListener(primaryStage, root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage(){
		return stage;
	}
	
	public void registerButtonClicked(ActionEvent e) throws IOException, URISyntaxException{
		Desktop.getDesktop().browse(new URI("http://127.0.0.1:8080/Login/WebContent/index.jsp"));
	}
	
	public void loginButtonClicked(ActionEvent e) throws IOException{
		userID=textField.getText();
		password=passwordField.getText();
		if(LoginModel.isLogin(userID,password)){
			username=(String)new MySQLQuery().queryValue("select u_name from users where u_id=?",
					new Object[]{userID});//未与register连接
			System.out.println("successfully login!");
//			ProgressFrom progressFrom=new ProgressFrom(stage);
			new PanelJump().Jump2NewPanel(e, true, "menu.fxml");
//			progressFrom.activateProgressBar();
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//			progressFrom.cancelProgressBar();
		}else{
			DialogDisplay.errorDialog("错误", "用户名或密码错误!");
			System.err.println("username or password is incorrect!");
		}
	}
	
	public void exitButtonClicked(ActionEvent e){
		System.exit(0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
