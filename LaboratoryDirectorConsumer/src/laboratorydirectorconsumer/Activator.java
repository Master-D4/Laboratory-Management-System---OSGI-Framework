package laboratorydirectorconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import directorservice.DirectorService;
import data.LabItems;


public class Activator implements BundleActivator {
	
	ServiceReference serviceReference;
	private boolean exit = false;
	Scanner input = new Scanner(System.in);
	

	@Override
	public void start(BundleContext context) throws Exception {
		
		System.out.println("==========LABORATORY DIRECTOR CONSUMER STARTED==========");
		
		serviceReference = context.getServiceReference(DirectorService.class.getName());
		@SuppressWarnings("unchecked")
		DirectorService directorService = (DirectorService) context.getService(serviceReference); //INSTANCE OF DIRECTOR SERVICE
		
		do {
			int option = 4;
			
			do {
				System.out.println("\n" + "----------WELCOME TO BILLING----------");
				
				System.out.println("Please select your option for continue >>");
				System.out.println("--Options--");
				System.out.println("(1) Available Lab Item List");
				System.out.println("(2) Made Lab Item Order");
				System.out.println("(3) Exit");
				
				System.out.println("\n" + "Input selected option >> ");
				option = input.nextInt();
				
				input.nextLine();
				if(option == 3) { //TO EXIT PROGRAMME
					exit = true;
				}
				
				if(option !=1 && option !=2 && option !=3) {
					System.out.println("Please select vallid option!!");
				}
				
			}while (option !=1 && option !=2 && option !=3);
			
			String toHome = null;
			String calculateNetBill = null;
			int labItemCount = -1;
			
			if(option == 1) { //LAB ITEM LIST VIEW PROCESS
				do {
					List<LabItems> labItemList = directorService.displayLabItems();
					
					System.out.println("--------------------------------LAB ITEM LIST--------------------------------------");
					System.out.println("Lab Item ID:"+"\t" +"Lab Item Name:"+"\t" + "Item Price:"+"\t" + "Discount (%):"+"\t" + "Net Price:"+"\t");
					
					for(LabItems tempLabItem : labItemList) {
						System.out.println(tempLabItem.getLabItemId()+"\t         "+tempLabItem.getLabItemName()+"\t         "+tempLabItem.getLabItemPrice()+"\t         "+tempLabItem.getDiscount()+"\t                  "+tempLabItem.getNetPrice()+"\t"+"\n");
						
						System.out.println("-------------------------------------------------------------------------------");
					}
					
					System.out.println("press '0' to go home OR press 'any key' to contonue >>");
					
					toHome = input.nextLine();
					
				}while(!(toHome.equals("0")));
				
			}
			
			else if(option == 2) {  //ORDERINNG VIEW AND PROCESS
				do {
					
					do {
						System.out.println("----------WELCOME TO LAB ITEMS'S ORDERING----------" + "\n");
						
						System.out.println("Enter the Lab Item Id : ");
						int id = input.nextInt();
						
						System.out.println("Enter quantity : ");
						int qty = input.nextInt();
						
						int output = directorService.generateBill(id, qty); //CONSUMES THE DIRECTORSERVICE generateBill()
						if(output == -1) {
							System.out.println("Please enter valid Lab Item ID!!");
						}
						else {
							labItemCount ++;
						}
						
						input.nextLine();
						System.out.println("press 'y' to calculate net bill OR press 'any key' to continue billing >>");
						calculateNetBill = input.nextLine();
						
					}while(!(calculateNetBill.equals("y")));
					
					System.out.println("\n" + "-------------------------------RECEIPT-------------------------------");
					
					String[][] billDetails = directorService.displayBillDetails();
					
					String format = "%-20s";
					System.out.printf(format, "Lab Item ID:");
					System.out.printf(format, "Lab Item Name:");
					System.out.printf(format, "Quantity:");
					System.out.printf(format, "Total:");
					System.out.printf("\n");
					
					for(int i = 0; i <= labItemCount; i++) {
						
						for(int j = 0; j < billDetails[i].length; j++) {
							System.out.printf(format, billDetails[i][j]);
						}
						
						System.out.println("");
						
					}
					
					System.out.println("                                                          ----------");
					System.out.println("Subtotal:                                                   " + directorService.displayTotalBillAmount());
					System.out.println("                                                          ----------");
					System.out.println("                                                          ----------");
					System.out.println("--------------------------------------------------------------------------------------");
					
					labItemCount =- 1;
					
					System.out.println("press '0' to go to home OR press 'any key' to continue >>");
					
					toHome = input.nextLine();
					
				}while(!(toHome.equals("0")));
			}
			
			else if(option == 3) {
				return;
			}
			
		}while(!exit);
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("==========LABORATORY DIRECTOR CONSUMER STOPPED==========");
		context.ungetService(serviceReference);
	}

}
