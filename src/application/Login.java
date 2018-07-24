package application;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable{
	@FXML
	private TextField account;
	@FXML
	private PasswordField passwd;
	@FXML
	private Button login;
	@FXML
	private Button reset;
	@FXML
	private ImageView imgleft;
	@FXML
	private ImageView imgicon;
	@FXML
	private ImageView imgclose;
	@FXML
	private AnchorPane rootpan;
	Parent root = null;
	
	public void login() {
		if(!("admin".equals(account.getText()) && "admin".equals(passwd.getText()))){
			System.out.println(account.getText());
			System.out.println(passwd.getText());
			return;
		}
		ObservableList<Stage> os = FXRobotHelper.getStages();
		Stage s = new Stage();
		Scene sc = new Scene(root, 1000, 600);
		s.setScene(sc);
//		s.setResizable(false);
//		s.setMaximized(true);
		s.setTitle("欢迎使用普联软件站级BOS系统----联系电话：0531-xxxxxxxxx");
		s.getIcons().add(new Image("/smalllogocanary.png"));
//		s.initStyle(StageStyle.TRANSPARENT);
		s.show();
		os.get(0).close();
	}
	public void enterPressed(KeyEvent e){
//		if(e.getCode() == KeyCode.ENTER){
//			login();
//		}
	}
	public void closeWin(){
		((Stage)rootpan.getScene().getWindow()).close();
//		DragUtil.addDragListener((Stage)rootpan.getScene().getWindow(), rootpan);
	}
	public void mouseOn(){
		rootpan.getScene().setCursor(Cursor.HAND);
	}
	public void mouseMove(){
		rootpan.getScene().setCursor(Cursor.DEFAULT);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		imgleft.setImage(new Image("imgleft1.jpg"));
		imgicon.setImage(new Image("imgicon.jpg"));
		imgclose.setImage(new Image("close2.png"));
//		Rectangle clip = new Rectangle(200, 350);
//		clip.setArcWidth(30);
//		clip.setArcHeight(30);
//		imgleft.setClip(clip);
		Circle cc = new Circle(imgicon.getFitHeight()/2, imgicon.getFitWidth()/2, 50.0);
		imgicon.setClip(cc);
		
		// 调整布局，使登录部分居中
//		apmaster.setTopAnchor(apinner, (Screen.getPrimary().getVisualBounds().getHeight()-220)/2.0);
//		apmaster.setLeftAnchor(apinner, (Screen.getPrimary().getVisualBounds().getWidth()-280)/2.0);
//		new DragListener((Stage)rootpan.getScene().getWindow()).enableDrag(rootpan);
		
		try {
			// 预加载主界面，提高系统进入速度，预加载后大概提高600ms
			root = FXMLLoader.load(getClass().getResource("Demo.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
