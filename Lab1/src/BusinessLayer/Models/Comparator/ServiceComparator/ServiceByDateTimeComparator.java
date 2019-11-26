package BusinessLayer.Models.Comparator.ServiceComparator;

import DataAccess.Models.Service;

import java.util.Comparator;

public class ServiceByDateTimeComparator implements Comparator<Service> {
        @Override
        public int compare(Service service1, Service service2){
                return service1.getDateTime().compareTo(service2.getDateTime());
        }
}
