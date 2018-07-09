package vo;

public class Comment {
	private int id, product, score;
	private String content, purchaser;
	
	public Comment(int id, int product, int score, String content, String purchaser) {
		super();
		this.id = id;
		this.product = product;
		this.score = score;
		this.content = content;
		this.purchaser = purchaser;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPurchaser() {
		return purchaser;
	}
	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
}
