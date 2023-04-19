

import java.util.Date;

public class shipment {
	private int shipmentId;
	private int productId;
	private Date arrivalDate;
	private Date requestDate;
	private int quantity;
	private String shipmentStatus;
	
	public shipment(int shipmentId, int productId, int quantity,Date arrivalDate, Date requestDate, 
			String shipmentStatus) {
		super();
		this.shipmentId = shipmentId;
		this.productId = productId;
		this.arrivalDate = arrivalDate;
		this.requestDate = requestDate;
		this.quantity = quantity;
		this.shipmentStatus = shipmentStatus;
	}
	
	
	public int getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	

}
