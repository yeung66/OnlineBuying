package util;

import java.sql.Date;

public class Order {
	private int id, product, quantity;
	private String purchaser, states;
	private Date startTime;
	
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
	
}
