package DataPresentation.EntityMenu;

import BusinessLayer.Services.CompanyService;
import DataAccess.Models.Company;

import java.util.Scanner;

public class CompanyMenu extends BaseMenu{

    private CompanyService _companyService = new CompanyService();
    @Override
    public void show() {
        int choice;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("----------Menu----------");
            System.out.println("1. Show Company sorted by Id");
            System.out.println("2. Show Company sorted by Name");
            System.out.println("Enter you choice");
            choice = in.nextInt();
        } while (choice > 2 || choice < 0 );
        switch (choice){
            case 1:
                _companyService.showCompaniesSortedById();
                break;
            case 2:
                _companyService.showCompaniesSortedByName();
                break;
        }
        return;
    }

    @Override
    public void update() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Id of Entity");
        int entityId = in.nextInt();
        Company resultEntity = _companyService.getEntityByID(entityId);
        System.out.println(resultEntity);
        if (resultEntity == null) {
            System.out.println("Not found entity with "+ entityId + " Id");
            return;
        }
        System.out.println("Enter Company Name :");
        String name = in.next();
        System.out.println("Enter discount:");
        double discount = in.nextDouble();

        resultEntity.setName(name);
        resultEntity.setDiscount(discount);
    }

    @Override
    public boolean delete() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Id of Entity");
        int entityId = in.nextInt();
        Company resultEntity = _companyService.getEntityByID(entityId);
        System.out.println(resultEntity);
        if (resultEntity == null) {
            System.out.println("Not found entity with "+ entityId + " Id");
            return false;
        }
        _companyService.removeEntity(resultEntity);
        return true;
    }

    @Override
    public void add() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter Company Name :");
        String name = in.next();
        System.out.println("Enter discount:");
        double discount = in.nextDouble();

        int lengthOfCollection = _companyService.getAmountOfEntities();
        Company lastCompany = _companyService.getEntityByPosition(lengthOfCollection - 1 );

        Company company= new Company(lastCompany == null? 0: lastCompany.getCompanyId() + 1, name, discount);

        _companyService.addEntity(company);
    }

    @Override
    public void saveChanges() {
        _companyService.saveEntities();
    }
}
