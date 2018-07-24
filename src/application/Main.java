package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
//			Scene scene = new Scene(root,1360,600);
////			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
////			primaryStage.setFullScreen(true);
//			primaryStage.centerOnScreen();
//			primaryStage.alwaysOnTopProperty();
//			primaryStage.setTitle("hello word");
//			primaryStage.show();
			
			Parent root = FXMLLoader.load(getClass().getResource("Demo.fxml"));
			Scene scene = new Scene(root, 550, 350);
			scene.setFill(Color.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			// 禁止缩放窗体
//			primaryStage.setResizable(false);
			// 全屏打开，可退出全屏
//			primaryStage.setFullScreen(true);
			// 最大化
			primaryStage.setMaximized(true);
			// 设置窗体风格
//			primaryStage.setAlwaysOnTop(true);
			primaryStage.setTitle("欢迎使用普联软件站级BOS系统----联系电话：0531-xxxxxxxxx");
			primaryStage.getIcons().add(new Image("/smalllogocanary.png"));
//			primaryStage.initStyle(StageStyle.TRANSPARENT);
			DragUtil.addDragListener(primaryStage, root);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
