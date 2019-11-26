package BusinessLayer.Services;

import BusinessLayer.Models.Comparator.ClientComparator.ClientByBankAccountComparator;
import BusinessLayer.Models.Comparator.ClientComparator.ClientByCompanyComparator;
import BusinessLayer.Models.Comparator.ClientComparator.ClientByIdComparator;
import BusinessLayer.Models.Comparator.ClientComparator.ClientBySurnameComparator;
import DataAccess.Models.Client;

public class ClientService extends BaseService<Client> {

    public ClientService(String fileName){
        super(fileName);
    }

    public ClientService(){
        super("./clients.txt");
    }

    public void showClientsSortedByBankAccount(){
        if (this._entities == null)
            loadEntities();
        this._entities.sort(new ClientByBankAccountComparator());
        for (Client client: _entities ) {
            System.out.println(client);
        }
    }
    public void showClientsSortedByCompany(){
        if (this._entities == null)
            loadEntities();
        this._entities.sort(new ClientByCompanyComparator());
        for (Client client: _entities ) {
            System.out.println(client);
        }
    }
    public void showClientsSortedBySurname(){
        if (this._entities == null)
            loadEntities();
        this._entities.sort(new ClientBySurnameComparator());
        for (Client client: _entities ) {
            System.out.println(client);
        }
    }

    public void showClientsSortedById(){
        if (this._entities == null)
            loadEntities();
        this._entities.sort(new ClientByIdComparator());
        for (Client client: _entities ) {
            System.out.println(client);
        }
    }


    public Client getEntityByID(int id){
        if (this._entities == null)
            loadEntities();
        for (Client entity: this._entities) {
            if(entity.getClientId() == id){
                return entity;
            }
        }
        return null;
    }
}
