package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import Entity.EditingCell;
import Entity.MyMenu;
import Entity.TableEntity;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.util.Callback;
/**
 * @author LZY
 * @version  0.0.8
 * 
 * 
 * */
public class Demo implements Initializable{

	@FXML
	private RadioButton radio1, radio2, radio3;
	private final ToggleGroup tg = new ToggleGroup();
	@FXML
	private ChoiceBox<String> choicebox;
	@FXML
    private ComboBox<String> combo1;
	@FXML
    private ComboBox<String> combo2;
	@FXML
	private WebView webview;
	@FXML
	private ColorPicker colorpicker;
	@FXML
	private TextArea textarea;
	@FXML
	private CheckBox check1;
	@FXML
	private CheckBox check2;
	@FXML
	private CheckBox check3;
	@FXML
	private CheckBox check4;
	@FXML
	private DatePicker datepicker;
	@FXML
	private Button button;
	@FXML
	private ProgressBar progressbar;
	@FXML
	private ProgressIndicator progressindicator;
	@FXML
	private Button button1;
	@FXML
	private MenuBar menubar;
	// table
	@FXML
	private TableView<TableEntity> tableview;
	@FXML
	private TableColumn<TableEntity, String> no;
	@FXML
	private TableColumn<TableEntity, String> account;
	@FXML
	private TableColumn<TableEntity, String> name;
	@FXML
	private TableColumn<TableEntity, String> partment;
	@FXML
	private TableColumn<TableEntity, String> job;
	@FXML
	private TextField foo, bar, result;
	@FXML
	private Button add, subtract, multiply;
	
	ObservableList<TableEntity> data;
	// 初始化界面,fxml加载后自动调用
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// 绑定单选组
		radio1.setToggleGroup(tg);
		radio1.setUserData("radio1");
		radio2.setToggleGroup(tg);
		radio2.setUserData("radio2");
		radio3.setToggleGroup(tg);
		radio3.setUserData("radio3");
		choicebox.setItems(FXCollections.observableArrayList("aaa", "bbb", "ccc"));
		combo1.setItems(FXCollections.observableArrayList("ddd", "eee", "fff"));
		// ccb2第一种赋值方式（效果：覆盖）
		combo2.setItems(FXCollections.observableArrayList("ddd", "eee", "fff", "ggg", "hhh"));
		// ccb2第二种赋值方式（效果：新增）
		ArrayList<String> al = new ArrayList<>();
		al.add("asfsaf");
		al.add("asfsaf1");
		al.add("asfsaf2");
		al.add("asfsaf3");
		combo2.getItems().addAll(al);
		
		combo1.setValue("admin");
        webview.getEngine().load("http:\\www.baidu.com");
        
        datepicker.setValue(LocalDate.now());
        // 使用lambda表达式向控件添加事件 ()->{}
        button1.setOnAction( (e) ->{
        	Alert all = new Alert(AlertType.CONFIRMATION);
        	all.setContentText("pressed");
        	all.setTitle("pressed");
        	all.show();
        });
        // 基础方式添加事件
//        button1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//            	System.out.println(1);
//            }
//        });
        // 动态构造菜单
        CreateMenuBar();
        // post后台交互
        add.setOnAction(e->{
        	this.post("add");
        });
        subtract.setOnAction(e->{
        	this.post("subtract");
        });
        multiply.setOnAction(e->{
        	this.post("multiply");
        });
        
        // 表格处理
//        no.setCellValueFactory(new PropertyValueFactory<>("no"));
        Callback<TableColumn<TableEntity, String>, TableCell<TableEntity, String>> cellFactory = (
                TableColumn<TableEntity, String> p) -> new EditingCell();

        // 自动添加行号
        no.setCellFactory((col) -> {
            TableCell<TableEntity, String> cell = new TableCell<TableEntity, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });
        // 设置account可编辑
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        account.setCellFactory(TextFieldTableCell.<TableEntity>forTableColumn());
        account.setOnEditCommit((t) -> {
            ((TableEntity) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAccount(t.getNewValue());
        });
        account.setCellFactory(cellFactory);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        partment.setCellValueFactory(new PropertyValueFactory<>("partment"));
        job.setCellValueFactory(new PropertyValueFactory<>("job"));
        String tablejson = "["  + JSON.toJSON(new TableEntity("admin", "admin", "master", "admin")).toString() 
        						+ JSON.toJSON(new TableEntity("root", "root", "master", "admin")).toString()
        						+ JSON.toJSON(new TableEntity("test", "test", "master", "test")).toString() 
        						+ JSON.toJSON(new TableEntity("lzy", "lzy", "master", "admin")).toString() 
        						+ JSON.toJSON(new TableEntity("admin", "admin", "master", "admin")).toString() 
        						+ JSON.toJSON(new TableEntity("root", "root", "master", "admin")).toString()
        						+ JSON.toJSON(new TableEntity("test", "test", "master", "test")).toString() 
        						+ JSON.toJSON(new TableEntity("lzy", "lzy", "master", "admin")).toString()
        						+ JSON.toJSON(new TableEntity("admin", "admin", "master", "admin")).toString() 
        						+ JSON.toJSON(new TableEntity("root", "root", "master", "admin")).toString()
        						+ JSON.toJSON(new TableEntity("test", "test", "master", "test")).toString() 
        						+ JSON.toJSON(new TableEntity("lzy", "lzy", "master", "admin")).toString()
        						+ JSON.toJSON(new TableEntity("admin", "admin", "master", "admin")).toString() 
        						+ JSON.toJSON(new TableEntity("root", "root", "master", "admin")).toString()
        						+ JSON.toJSON(new TableEntity("test", "test", "master", "test")).toString() 
        						+ JSON.toJSON(new TableEntity("lzy", "lzy", "master", "admin")).toString()
        						+ "]";
        ArrayList<TableEntity> datas = JSON.parseObject(tablejson, new TypeReference<ArrayList<TableEntity>>(){});
        data = FXCollections.observableArrayList(datas);
        tableview.setItems(data);
	}
	
	public void addTable(){
        data.add(new TableEntity("", "", "", ""));
	}
	public void deleteTable(){
		// 判断是否有选中的行
		if(!tableview.getSelectionModel().isEmpty()){
			// 获取当前选中行信息
			System.out.println("当前删除的行是：" + ((TableEntity)tableview.getSelectionModel().getSelectedItem()).getAccount());
			// 删除选中行
			data.remove((TableEntity)tableview.getSelectionModel().getSelectedItem());
		}else{
			// 提示用户未选择
		}
		
	}
	
	
	
	public void getColor(){
		Color color = colorpicker.getValue();
		// 返回值示例 0x29d3af87 29d3af：颜色RGB 87：透明度16进制
		System.out.println(color.toString());
		
	}
	public void getRadio(){
		Toggle t = tg.getSelectedToggle();
		System.out.println(t.getUserData().toString());
	}
	
	public void getTextArea(KeyEvent e){
		if(e.getCode() == KeyCode.ENTER){
			System.out.println(textarea.getText());
		}
	}
	public void getChecked(){
		System.out.println(check1.selectedProperty().get()+" "+check2.selectedProperty().get()
				+" "+check3.selectedProperty().get()+" "+check4.selectedProperty().get());
	}
	public void getDate(){
//		//在类的构造方法中设置指定日期
//		datepicker = new DatePicker(LocalDate.of(1998, 10, 8));
//
//		//通过setValue方法来设置指定日期checkInDatePicker.setValue(LocalDate.of(1998, 10, 8));
//		//设置日历中最小的可用日期
//		datepicker.setValue(LocalDate.MIN);
//		//设置日历中最大的可用日期
//		datepicker.setValue(LocalDate.MAX);
//		//设置当前日期
//		datepicker.setValue(LocalDate.now());
	}
	
	// 进度条异步更新
	public void progressbar(){
		 //方法1 注：Thread.sleep不能放在Platform.runLater中，Platform.runLater方法会阻塞界面主进程！
		new Thread(new Runnable() {
		    @Override public void run() {
			    for (int i = 1; i <= 50; i++) {
			    	try {
						Thread.sleep(200L);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        final int counter = i;
			        // lambda表达式()->{}
			        Platform.runLater(()->{
			        	progressbar.setProgress(counter / 50.0);
		                progressindicator.setProgress(counter / 50.0);
			        });
			    }
		    }
		}).start();
		// 方法2
//		progressbar.progressProperty().bind(service.progressProperty());
//		service.restart();
//		progressindicator.progressProperty().bind(service.progressProperty());
//		service.restart();
	}
	
//    Service<Integer> service = new Service<Integer>() {
//        @Override
//        protected Task<Integer> createTask() {
//            return new Task<Integer>() {
//                @Override
//                protected Integer call() throws Exception {
//                    int i = 0;
//                    while (i++ < 100) {
//                        updateProgress(i, 100);
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    return null;
//                };
//            };
//        }
//    };
	
	
	// 构造后台传值------菜单项
	public void CreateMenuBar(){
		// 构造json
		String menustr = "[{\"MenuName\":\"主菜单1\",\"isDetail\":\"false\",\"child\":[{\"MenuName\":\"主1-二级1\",\"isDetail\":\"true\",\"FxmlSrc\":\"/src/application/xxxx.fxml\"},{\"MenuName\":\"主1-二级2\",\"isDetail\":\"false\",\"child\":[{\"MenuName\":\"主1-二级2-三级1\",\"isDetail\":\"true\",\"FxmlSrc\":\"/src/application/xxx.fxml\"}]}]},"
						+ "{\"MenuName\":\"主菜单2\",\"isDetail\":\"false\",\"child\":[{\"MenuName\":\"主2-二级1\",\"isDetail\":\"true\",\"FxmlSrc\":\"/src/application/sssx.fxml\"},{\"MenuName\":\"主2-二级2\",\"isDetail\":\"false\",\"child\":[{\"MenuName\":\"主2-二级2-三级1\",\"isDetail\":\"true\",\"FxmlSrc\":\"/src/application/ssssx.fxml\"}]}]}]";
		ArrayList<MyMenu> menulist = JSON.parseObject(menustr, new TypeReference<ArrayList<MyMenu>>(){});
		menulist.forEach( menu ->{
			menubar.getMenus().add((Menu)CreateMenu(menu));
		});
	}
	
	// 递归生成菜单
	public MenuItem CreateMenu(MyMenu hm){
		if(hm.getIsDetail()){
			MenuItem mi = new MenuItem(hm.getMenuName());
			mi.setOnAction((e)->{
				// 判断tab是否已打开，如果已打开跳转到tab页，否则新建tab页并加载对应fxml文件
				System.out.println(hm.getFxmlSrc());
				Alert al = new Alert(AlertType.INFORMATION);
				al.setTitle("pressed");
				al.setContentText(hm.getFxmlSrc());
				al.show();
			});
			return mi;
		}else{
			Menu menu = new Menu(hm.getMenuName());
			hm.getChild().forEach((mymenu)->{
				menu.getItems().add(CreateMenu(mymenu)); 
			});
			return menu;
		}
		
	}
	
	public void post(String mode){
		HashMap<String, String> map = new HashMap<>();
		map.put("foo", foo.getText());
		map.put("bar", bar.getText());
		JSONObject jo = null;
		try {
			jo = PostServer.doPostServer("http://localhost:8086/demo/" + mode, map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setText(jo.getString("data"));
	}
}
