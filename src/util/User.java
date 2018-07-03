package util;

public class User {	
	private String id, pwd, info, add;
	private int tel, sex, right;
	
	public User(String id, String pwd, String info, String add, int tel, int sex, int right) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.info = info;
		this.add = add;
		this.tel = tel;
		this.sex = sex;
		this.right = right;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

}
