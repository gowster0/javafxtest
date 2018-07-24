package Entity;


public class TableEntity {
	private String no;
	private String account;
	private String name;
	private String partment;
	private String job;
	public TableEntity( String account, String name, String partment, String job){
		this.account = account;
		this.name = name;
		this.partment = partment;
		this.job = job;
//		this.editbutton = new Button(button);
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPartment() {
		return partment;
	}
	public void setPartment(String partment) {
		this.partment = partment;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
//	public Object getEditbutton() {
//		return editbutton;
//	}
//	public void setEditbutton(Button editbutton) {
//		this.editbutton = editbutton;
//	}
	
}
