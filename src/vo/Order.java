package vo;


import java.sql.Date;

public class Order {
	private int id, product, quantity;
	private String purchaser, states;
	private Date startTime;

	public Order() {
	}

	public Order(int id, int product, int quantity, String purchaser, String states, Date startTime) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.purchaser = purchaser;
		this.states = states;
		this.startTime = startTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduct() {
		return product;
	}

	public void setProduct(int product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getStates() {
		return states;
	}
	
	public void setStates(String states) {
		this.states = states;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getStatus() {
		if(states.equals("0"))
			return "未发货";
		else if(states.equals("1"))
			return "已发货";
		else if(states.equals("2"))
			return "退货中";
		else if(states.equals("3"))
			return "已退货";
		else if(states.equals("4"))
			return "已签收";
		else if(states.equals("5"))
			return "已评价";
		else
			return "";
	}
}
