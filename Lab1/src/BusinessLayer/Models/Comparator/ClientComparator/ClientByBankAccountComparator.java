package BusinessLayer.Models.Comparator.ClientComparator;

import DataAccess.Models.Client;

import java.util.Comparator;

public class ClientByBankAccountComparator implements Comparator<Client> {
    @Override
    public int compare(Client client1, Client client2){
        return (int) Math.round((client1.getBankAccount() - client2.getBankAccount()) * 100);
    }
}
