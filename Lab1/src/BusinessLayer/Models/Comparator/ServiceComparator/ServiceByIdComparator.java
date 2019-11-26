package BusinessLayer.Models.Comparator.ServiceComparator;

import DataAccess.Models.Service;

import java.util.Comparator;

public class ServiceByIdComparator implements Comparator<Service> {
    @Override
    public int compare(Service service1, Service service2){
        return service1.getServiceId() - service2.getServiceId();
    }
}
