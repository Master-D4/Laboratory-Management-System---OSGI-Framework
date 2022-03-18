package directorserviceimpl;

import java.util.List;

import data.LabItems;
import directorservice.DirectorService;
import labitemstore.LabItemStore;

public class DirectorServiceImpl implements DirectorService {
	
	private List<LabItems> labItemList = LabItemStore.labItemsList; //LAB ITEM LIST DETAILS
	private double bill; //STORE BILL VALUE
	private String[][] billDetails = new String[10][4]; //TO STORE TAKEN LAB ITEMS'S DETAILS (ASSUPTION : ONLY 10 DIFFERET ITEMS ALLOWED FOR 1 ORDER)
	private int count = -1; //STORE LAB ITEMS COUNT (STARTING FROM 0)
	

	@Override
	public List<LabItems> displayLabItems() {
		return LabItemStore.labItemsList;
	}

	@Override
	public int generateBill(int iId, int qty) {
		
		boolean valid = false;
		LabItems currentLabItem = null;
		
		for(LabItems tempLabItem : labItemList) {
			if(iId == tempLabItem.getLabItemId()) {
				currentLabItem = tempLabItem;
				valid = true;
				count ++;
				break;
			}
		}
		if(valid) {
			this.bill = this.bill + (currentLabItem.getNetPrice() * qty);
			
			billDetails[count][0] = Integer.toString(iId);
			billDetails[count][1] = currentLabItem.getLabItemName();
			
			billDetails[count][2] = Integer.toString(qty);
			billDetails[count][3] = Double.toString((currentLabItem.getNetPrice() * qty));
			
			return 1;
		}
		else {
			return -1;
		}
		
	}

	@Override
	public double displayTotalBillAmount() {
		
		double totalBill = this.bill;
		newBill();
		
		return totalBill;
		
	}

	@Override
	public String[][] displayBillDetails() {
		return billDetails;
	}
	
	
	public void newBill() {
		this.bill = 0;
		this.count = -1;
	}

	
}
