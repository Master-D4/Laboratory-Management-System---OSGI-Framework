package supplierservice;

import java.util.List;

import data.LabItems;

//SERVICES WHICH SUPPLIER CONSUMER NEEDS
public interface SupplierService {
	
	public int addItems(String itemName, double itemPrice, double itemDiscount); //ADD NEW ITEM TO SERVICE LIST
	public int updateItems(String updateItemName, double updateItemPrice, double updateItemDiscount); //UPDATE ITEM DETAILS
	public int removeItems(String itemName); //REMOVE ITEM FROM LIST BY NAME
	public int searchItems(String itemName); //SEARCH ITEM BY NAME
	
	public List<LabItems> listItems(); //RETUEN ITEM LIST

}
