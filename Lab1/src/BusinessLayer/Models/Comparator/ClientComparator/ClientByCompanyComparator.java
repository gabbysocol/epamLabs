package BusinessLayer.Models.Comparator.ClientComparator;

import DataAccess.Models.Client;

import java.util.Comparator;

public class ClientByCompanyComparator implements Comparator<Client> {
    @Override
    public int compare(Client client1, Client client2){
        return client1.getCompanyId() - client2.getCompanyId();
    }
}

