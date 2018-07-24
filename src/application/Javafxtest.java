package application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Javafxtest {
	@FXML
	private TextField money1;
	@FXML
	private TextField money2;
	@FXML
	private TextField money3;
	@FXML
	private Button button1;
	@FXML
	private ChoiceBox<String> drop;
	
	public void clickButton(){
		System.out.println("money1 = "+this.money1.getText());
		System.out.println("money2 = "+this.money2.getText());
		Map<String, String> map = new HashMap<String, String>();
		map.put("foo", this.money1.getText());
		map.put("bar", this.money2.getText());
		JSONObject js = null;
		try{
			js = PostServer.doPostServer("http://localhost:8086/demo/add", map);
		}catch (IOException e){
			return;
		}
		this.money3.setText(js.getString("data"));
		
		drop.setItems(FXCollections.observableArrayList("first", "secend", "third", "fouce"));
	}
	
}
