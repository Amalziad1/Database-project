 

public class queryProduct {
	private int productId;
	private String productName;
	private int purchases;

	public queryProduct(int productId, String productName,int purchases) {
		this.productId = productId;
		this.productName = productName;
		this.purchases=purchases;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPurchases() {
		return purchases;
	}

	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}

}
