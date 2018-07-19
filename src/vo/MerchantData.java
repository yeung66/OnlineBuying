package vo;

public class MerchantData {

	private String type;
	private int num;
	private double money;
	private double totMoney;
	private String month;

	public MerchantData(String type, int num, double money, double totMoney, String month) {
		super();
		this.type = type;
		this.num = num;
		this.money = money;
		this.totMoney = totMoney;
		this.month = month;
	}

	public MerchantData() {
		num = 0;
		money = totMoney = 0;
		type = "0";
		month = "0";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
