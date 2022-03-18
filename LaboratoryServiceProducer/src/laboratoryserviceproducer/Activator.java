package laboratoryserviceproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import directorservice.DirectorService;
import directorserviceimpl.DirectorServiceImpl;
import supplierservice.SupplierService;
import supplierserviceimpl.SupplierServiceImpl;

public class Activator implements BundleActivator {

	ServiceRegistration serviceRegistration;

	
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("=============LABORATORY SERVICE STARTED=============");
		
		SupplierService supplierService = new SupplierServiceImpl();
		//REGISTER THE SUPPLIER SERVICE
		serviceRegistration = context.registerService(SupplierService.class.getName(), supplierService, null);
		
		DirectorService directorService = new DirectorServiceImpl();
		//REGISTER THE DIRECTOR SERVICE
		serviceRegistration = context.registerService(DirectorService.class.getName().toString(), directorService, null);
		
	}
	

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("=============LABORATORY SERVICE STOPPED=============");
		serviceRegistration.unregister();
	}

}
