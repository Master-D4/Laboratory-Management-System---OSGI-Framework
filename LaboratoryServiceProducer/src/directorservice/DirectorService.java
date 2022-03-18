package directorservice;

import java.util.List;

import data.LabItems;

//SERVICES WHICH DIRECTOR CONSUMER NEEDS
public interface DirectorService {
	
	public List<LabItems> displayLabItems(); //RETURN THE LAB ITEM LIST WITH LAB ITEM DETAILS
	public int generateBill(int iId, int qty); //CALCULATES BILL
	public double displayTotalBillAmount(); //DISPLAY TOTAL AMOUNT
	public String[][] displayBillDetails(); //DISPLAY GIVEN LAB ITEM LIST WITH DETAILS

}
