package laboratorysupplierconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import data.LabItems;
import supplierservice.SupplierService;

public class Activator implements BundleActivator {
	
	ServiceReference serviceReference;
	Scanner input = new Scanner(System.in);
	boolean exit = false;
	

	@Override
	public void start(BundleContext context) throws Exception {
		
		System.out.println("==========LABORATORY SUPPLIER CONSUMER STARTED==========");
		
		serviceReference = context.getServiceReference(SupplierService.class.getName());
		@SuppressWarnings("unchecked")
		SupplierService supplierService = (SupplierService) context.getService(serviceReference);
		
		do {
			int option = 7;
			
			do {
				System.out.println("\n" + "----------WELCOME TO SUPPLIER SERVICE----------");
				
				System.out.println("Please select any option to continue >>");
				System.out.println("<< options >>");
				System.out.println("(1) Add new Lab Item");
				System.out.println("(2) Update an exsisting Lab Item");
				System.out.println("(3) Delete an exsisting Lab Item");
				System.out.println("(4) List Lab Items");
				System.out.println("(5) Search Lab Item by name");
				System.out.println("(6) Exit");
				
				System.out.println("\n" + "Enter your option >>");
				option = input.nextInt();
				
				input.nextLine();
				
				if(option == 6) {
					exit = true;
				}
				
				if(option !=1 && option !=2 && option !=3 && option !=4 && option !=5 && option !=6) {
					System.out.println("Please enter valid option!!");
				}
			
			}while(option !=1 && option !=2 && option !=3 && option !=4 && option !=5 && option !=6);
			
			String toHome = null;
			
			if(option == 1) { //ADDING PROCESS
				
				do {
					System.out.println("Lab Item Name : ");
					String serviceName = input.nextLine();
					
					System.out.println("Lab Item Price : ");
					double servicePrice = input.nextDouble();
					
					System.out.println("Discount : ");
					double serviceDiscount = input.nextDouble();
					
					input.nextLine();
					
					int output = supplierService.addItems(serviceName, servicePrice, serviceDiscount);
					String message = (output == 1) ? "Successfully added the Lab Item!!" : "Please enter valid name!!";
					System.out.println(message);
					
					System.out.println("press '0' to go back to home OR press 'any key' to continue >>");
					toHome = input.nextLine();
					
				}while(!(toHome.equals("0")));
				
			}
			
			
			else if(option == 2) { //UPDATING PROCESS
				
				do {
					System.out.println("Lab Item Name : ");
					String updateServiceName = input.nextLine();
					
					System.out.println("Lab Item Price : ");
					double updateServicePrice = input.nextDouble();
					
					System.out.println("Discount : ");
					double updateServiceDiscount = input.nextDouble();
					
					input.nextLine();
					
					int output = supplierService.updateItems(updateServiceName, updateServicePrice, updateServiceDiscount);
					String message = (output == 1) ? "Successfully updated the Lab Item!!" : "Please enter valid name!!";
					System.out.println(message);
					
					System.out.println("press '0' to go back to home OR press 'any key' to continue >>");
					toHome = input.nextLine();
					
				}while(!(toHome.equals("0")));
			}
			
			
			else if(option == 3) { //REMOVING PROCESS
				
				do {
					System.out.println("Enter the Lab Item name : ");
					String serviceName = input.nextLine();
					
					int output = supplierService.removeItems(serviceName);
					String message = (output == 1) ? "Successfully deleted the Lab Item!!" : "Please enter valid name!!";
					System.out.println(message);
					
					System.out.println("press '0' to go back to home OR press 'any key' to continue >>");
					toHome = input.nextLine();
					
				}while(!(toHome.equals("0")));
				
			}
			
			
			else if(option == 4) { //ITEM LIST DISPLAY PROCESS
				
				do {
					List<LabItems> labItemList = supplierService.listItems();
					
					System.out.println("--------------------------------------LAB ITEM LIST--------------------------------------");
					System.out.println("\n" + "Lab Item ID:"+"\t" +"Lab Item Name:"+"\t"+ "Item Price:"+"\t" + "Discount (%):"+"\t" + "Net Price:"+"\t");
					
					for(LabItems tempLabItems : labItemList) {
						
						System.out.println(tempLabItems.getLabItemId()+"\t         "+tempLabItems.getLabItemName()+"\t         "+tempLabItems.getLabItemPrice()+"\t         "+tempLabItems.getDiscount()+"\t                  "+tempLabItems.getNetPrice()+"\t"+"\n");
					
					}
					
					System.out.println("-----------------------------------------------------------------------------------------");
					
					System.out.println("press '0' to go back to home OR press 'any key' to continue >>");
					toHome = input.nextLine();
					
				}while(!(toHome.equals("0")));
				
			}
			
			
			else if(option == 5) { //ITEM SEARCHINGG PROCESS
				
				do {
					System.out.println("Enter the Lab Item name : ");
					String serviceName = input.nextLine();
					
					int output = supplierService.searchItems(serviceName);
					String message = (output == 1) ? "Lab Item found!!" : "Lab Item not found!!";
					System.out.println(message);
					
					System.out.println("press '0' to go back to home OR press 'any key' to continue >>");
					toHome = input.nextLine();
					
				}while(!(toHome.equals("0")));
				
			}
			
			
			else if(option == 6) { //EXIT PROCESS
				return;
			}
			
		
		}while(!exit);
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("==========LABORATORY SUPPLIER CONSUMER STOPPED==========");
		context.ungetService(serviceReference);
	}
	
}
