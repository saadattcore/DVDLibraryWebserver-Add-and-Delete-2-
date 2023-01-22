

public class DVD {
	
	private int id;
	private String sku;
	private String description;
	private String category;
  	private double price;
	
	
	public DVD(int ID, String sku, String description, String category, double price
	) {
		
		this.id = ID;
		this.sku = sku;
		this.description = description;
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
		return "DVD [ID=" + this.id + ", SKU=" + this.sku + ", Description=" + this.description + ", Category=" + this.category + ",Price=" + this.price + "]";
	}
	
}
