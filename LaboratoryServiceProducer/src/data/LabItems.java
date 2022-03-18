package data;

//LABITEM MODEL CLASS
public class LabItems {
	
	private int labItemId;
	private String labItemName;
	private double labItemPrice;
	private double discount;
	private double netPrice;
	
	
	public LabItems(int labItemId, String labItemName, double labItemPrice, double discount) {
		super();
		this.labItemId = labItemId;
		this.labItemName = labItemName;
		this.labItemPrice = labItemPrice;
		this.discount = discount;
		calculateNetPrice();
	}


	public int getLabItemId() {
		return labItemId;
	}


	public void setLabItemId(int labItemId) {
		this.labItemId = labItemId;
	}


	public String getLabItemName() {
		return labItemName;
	}


	public void setLabItemName(String labItemName) {
		this.labItemName = labItemName;
	}


	public double getLabItemPrice() {
		return labItemPrice;
	}


	public void setLabItemPrice(double labItemPrice) {
		this.labItemPrice = labItemPrice;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public double getNetPrice() {
		return netPrice;
	}


	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}
	
	
	public void calculateNetPrice() {
		this.netPrice = labItemPrice * ((100.00 - discount) / 100);
	}
	

}
