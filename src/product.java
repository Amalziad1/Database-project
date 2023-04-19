
public class product {
	
	private int serial_num;
	private String product_name;
	private int stock;
	private double price;
	private double cost;
	private String supplier_name;
	private String category;
	
	public product() {
		super();
	}

	

	public product( int serial_num, String product_name ,int stock, double price,
	double cost, String supplier_name, String category)
	 {
		super();
		this.serial_num = serial_num;
		this.product_name = product_name;
		this.stock = stock;
		this.price = price;
		this.cost = cost;
		this.supplier_name = supplier_name;
		this.category= category;
		}






	public int getSerial_num() {
		return serial_num;
	}



	public void setSerial_num(int serial_num) {
		this.serial_num = serial_num;
	}



	public String getProduct_name() {
		return product_name;
	}



	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public double getCost() {
		return cost;
	}



	public void setCost(double cost) {
		this.cost = cost;
	}



	public String getSupplier_name() {
		return supplier_name;
	}



	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}


	
	
}
