package supplierserviceimpl;

import java.util.List;

import data.LabItems;
import labitemstore.LabItemStore;
import supplierservice.SupplierService;

public class SupplierServiceImpl implements SupplierService {

	@Override
	public synchronized int addItems(String itemName, double itemPrice, double itemDiscount) {
		
		LabItems newItems = new LabItems(LabItemStore.labItemsList.size() + 1, itemName, itemPrice, itemDiscount);
		LabItemStore.labItemsList.add(newItems);
		
		return 1;
	}

	@Override
	public synchronized int updateItems(String updateItemName, double updateItemPrice, double updateItemDiscount) {
		
		LabItems itemToBeUpdate = null;
		boolean invalid = true;
		int count = -1;
		
		for(LabItems tempItem : LabItemStore.labItemsList) {
			count ++;
			
			if(tempItem.getLabItemName().equalsIgnoreCase(updateItemName)) {
				itemToBeUpdate = tempItem;
				invalid = false;
				break;
			}
		}
		if(!invalid) {
			itemToBeUpdate.setLabItemName(updateItemName);
			itemToBeUpdate.setLabItemPrice(updateItemPrice);
			itemToBeUpdate.setDiscount(updateItemDiscount);
			itemToBeUpdate.calculateNetPrice();
			
			LabItemStore.labItemsList.set(count, itemToBeUpdate);
			return 1;
		}
		else {
			return -1;
		}
		
	}

	@Override
	public synchronized int removeItems(String itemName) {
		
		boolean invalid = true;
		int count = -1;
		
		for(LabItems tempItem : LabItemStore.labItemsList) {
			count ++;
			if(tempItem.getLabItemName().equalsIgnoreCase(itemName)) {
				invalid = false;
				break;
			}
		}
		if(!invalid) {
			LabItemStore.labItemsList.remove(count);
			return 1;
		}
		else {
			return -1;
		}
		
	}

	@Override
	public int searchItems(String itemName) {
		
		boolean valid = false;
		
		for(LabItems tempItem : LabItemStore.labItemsList) {
			if(tempItem.getLabItemName().equalsIgnoreCase(itemName)) {
				valid = true;
				break;
			}
		}
		if(valid) {
			return 1;
		}
		else {
			return -1;
		}
		
	}

	@Override
	public List<LabItems> listItems() {
		return LabItemStore.labItemsList;
	}

}
