package vo;

public class Product {

	private int id, num, comnum;
	private double score, price;
	private String name, path;
	private String owner, information;
	private String status, type;

	public Product(int id, double price, int num, double score, int comnum, String name, String owner, String path,
			String information, String type) {
		super();
		this.id = id;
		this.num = num;
		this.comnum = comnum;
		this.score = score;
		this.price = price;
		this.name = name;
		this.path = path;
		this.owner = owner;
		this.information = information;
		this.type = type;
	}

	public Product() {
	}

	public Product(int id, double price, int num, double score, int comnum, String name, String owner, String path,
			String information) {
		super();
		this.id = id;
		this.price = price;
		this.num = num;
		this.score = score;
		this.comnum = comnum;
		this.name = name;
		this.owner = owner;
		this.path = path;
		this.information = information;
	}

	public Product(int id, double price, int num, double score, int comnum, String name, String owner, String path) {
		super();
		this.id = id;
		this.price = price;
		this.num = num;
		this.score = score;
		this.comnum = comnum;
		this.name = name;
		this.owner = owner;
		this.path = path;
		this.information = "";
	}

	public String getpType() {
		return type;
	}

	public String getType() {
		if(type==null)
			return type;
		else if (type.equals("0"))
			return "文具卡片";
		else if (type.equals("1"))
			return "特色美食";
		else if (type.equals("2"))
			return "服饰箱包";
		else if (type.equals("3"))
			return "居家生活";
		else if (type.equals("4"))
			return "数码电器";
		return "";
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getComnum() {
		return comnum;
	}

	public void setComnum(int comnum) {
		this.comnum = comnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}



}
