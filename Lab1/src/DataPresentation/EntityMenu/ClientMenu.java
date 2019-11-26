package DataPresentation.EntityMenu;

import BusinessLayer.Services.ClientService;
import DataAccess.Models.Client;

import java.util.Scanner;

public class ClientMenu extends BaseMenu {
    private ClientService _clientService = new ClientService();
    @Override
    public void show() {
        int choice;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("----------Menu----------");
            System.out.println("1. Show Client sorted by Surname");
            System.out.println("2. Show Client sorted by Bank Account");
            System.out.println("3. Show Client sorted by Company");
            System.out.println("4. Show Client sorted by Id");
            System.out.println("Enter you choice");
            choice = in.nextInt();
        } while (choice > 4 || choice < 0 );
        switch (choice){
            case 1:
                _clientService.showClientsSortedBySurname();
                break;
            case 2:
                _clientService.showClientsSortedByBankAccount();
                break;
            case 3:
                _clientService.showClientsSortedByCompany();
                break;
            case 4:
                _clientService.showClientsSortedById();
                break;
        }
        return;
    }

    @Override
    public void update() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Id of Entity");
        int entityId = in.nextInt();
        Client resultEntity = _clientService.getEntityByID(entityId);
        System.out.println(resultEntity);
        if (resultEntity == null) {
            System.out.println("Not found entity with "+ entityId + " Id");
            return;
        }

        System.out.println("Enter Client Name :");
        String name = in.next();
        System.out.println("Enter Client Surname :");
        String surname = in.next();

        System.out.println("Enter bankAccount:");
        double bankAccount = in.nextDouble();

        System.out.println("Enter tariff Id:");
        int tariff = in.nextInt();

        System.out.println("Enter company Id:");
        int company = in.nextInt();

        resultEntity.setClientId(company);
        resultEntity.setName(name);
        resultEntity.setBankAccount(bankAccount);
        resultEntity.setSurname(surname);
        resultEntity.setTariffPlanId(tariff);


    }

    @Override
    public boolean delete() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Id of Entity");
        int entityId = in.nextInt();
        Client resultEntity = _clientService.getEntityByID(entityId);
        System.out.println(resultEntity);
        if (resultEntity == null) {
            System.out.println("Not found entity with "+ entityId + " Id");
            return false;
        }
        _clientService.removeEntity(resultEntity);
        return true;
    }

    @Override
    public void add() {

        Scanner in = new Scanner(System.in);

        System.out.println("Enter Client Name :");
        String name = in.next();
        System.out.println("Enter Client Surname :");
        String surname = in.next();

        System.out.println("Enter bankAccount:");
        double bankAccount = in.nextDouble();

        System.out.println("Enter tariff Id:");
        int tariff = in.nextInt();

        System.out.println("Enter company Id:");
        int company = in.nextInt();

        int lengthOfCollection = _clientService.getAmountOfEntities();
        Client lastClient = _clientService.getEntityByPosition( lengthOfCollection - 1 );

        Client client= new Client(lastClient == null? 0 : lastClient.getClientId() + 1, name, surname, bankAccount, tariff, company, true );

        _clientService.addEntity(client);
    }

    @Override
    public void saveChanges() {
        _clientService.saveEntities();
    }
}
