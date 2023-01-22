

public class Product {

	private int id;
	private String sku;
	private String description;
	private String type1;
	private String type2;
	private String category;
	private double price;


	public Product(int ID, String sku, String description, String type1, String type2, double price, String category) {

		this.id = ID;
		this.sku = sku;
		this.description = description;
		this.type1 = type1;
		this.type2 = type2;
		this.category = category;
		this.price = price;
	}
	public int getID() {
		return this.id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getSKU() {
		return this.sku;
	}
	public void setSKU(String sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getType1() {
		return this.type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return this.type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getCategory() {
		return this.category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return this.price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [ID= " + this.id + ", SKU= " + this.sku + ", Description= " + this.description + ", Type 1= " + this.type1 + ", Type 2= " + this.type2 + ", Category= " + this.category + ", Price= " + this.price + "]";
	}

}
