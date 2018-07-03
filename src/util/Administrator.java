package util;

public class Administrator {
	private int id;
	private String pwd;
	
	public Administrator(int id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
