package Entity;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;

public class MyMenu extends JSON{
	private String MenuName;
	private String FxmlSrc;
	private Boolean isDetail;
	private ArrayList<MyMenu> child;
	
	public String getMenuName() {
		return MenuName;
	}
	public void setMenuName(String menuName) {
		MenuName = menuName;
	}
	public String getFxmlSrc() {
		return FxmlSrc;
	}
	public void setFxmlSrc(String fxmlSrc) {
		FxmlSrc = fxmlSrc;
	}
	public Boolean getIsDetail() {
		return isDetail;
	}
	public void setIsDetail(Boolean isDetail) {
		this.isDetail = isDetail;
	}
	public ArrayList<MyMenu> getChild() {
		return child;
	}
	public void setChild(ArrayList<MyMenu> child) {
		this.child = child;
	}
	
	
	
}
