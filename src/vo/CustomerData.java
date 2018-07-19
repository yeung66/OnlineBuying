package vo;

public class CustomerData {

	private String type;
	private double money;
	private double totMoney;
	private String month;

	public CustomerData() {
		money = totMoney = 0;
		type = "0";
		month = "0";
	}

	public CustomerData(String type, double money, double totMoney, String month) {
		super();
		this.type = type;
		this.money = money;
		this.totMoney = totMoney;
		this.month = month;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getTotMoney() {
		return totMoney;
	}

	public void setTotMoney(double totMoney) {
		this.totMoney = totMoney;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
