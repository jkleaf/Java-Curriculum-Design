package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginDemo1 extends Application {

	@FXML Button registerButton;
	@FXML TextField textField;
	@FXML PasswordField passwordField;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().
					getResource("LoginDemo1.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void registerButtonClicked(ActionEvent e) throws IOException, URISyntaxException{
		Desktop.getDesktop().browse(new URI("http://127.0.0.1:8080/Login/WebContent/index.jsp"));
	}
	
	public void loginButtonClicked(ActionEvent e) throws IOException{
//		Main.main(null);
		if(LoginModel.isLogin(textField.getText(), passwordField.getText())){
			System.out.println("successfully login!");
			new PanelJump().Jump2NewPanel(e, true, "the b.fxml", 935, 565);
		}else{
			System.err.println("username or password is incorrect!");
		}
	}
}
