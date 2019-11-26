package DataPresentation.EntityMenu;

import BusinessLayer.Services.TariffPlanService;
import DataAccess.Models.Debit;
import DataAccess.Models.TariffPlan;

import java.util.Scanner;

public class TariffPlanMenu extends BaseMenu {

    private TariffPlanService _tariffPlan = new TariffPlanService();
    @Override
    public void show() {
        int choice;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("----------Menu----------");
            System.out.println("1. Show Tariff Plans sorted by Id");
            System.out.println("2. Show Tariff Plans sorted by Subscription Free");
            System.out.println("Enter you choice");
            choice = in.nextInt();
        } while (choice > 2 || choice < 0 );
        switch (choice){
            case 1:
                _tariffPlan.showTariffPlanSortedById();
                break;
            case 2:
                _tariffPlan.showTariffPlanSortedBySubscriptionFree();
                break;
        }
        return;
    }

    @Override
    public void update() {


        Scanner in = new Scanner(System.in);
        System.out.println("Enter Id of Entity");
        int entityId = in.nextInt();
        TariffPlan resultEntity = _tariffPlan.getEntityByID(entityId);
        System.out.println(resultEntity);
        if (resultEntity == null) {
            System.out.println("Not found entity with "+ entityId + " Id");
            return;
        }
        System.out.println("Enter Name :");
        String name = in.next();

        System.out.println("Enter Subscription Fee :");
        double subscriptionFee = in.nextDouble();

        System.out.println("Enter SMS price :");
        double smsPrice = in.nextDouble();
        System.out.println("Enter Call price :");
        double callPrice = in.nextDouble();

        resultEntity.setSubscriptionFee(subscriptionFee);
        resultEntity.setSmsPrice(smsPrice);
        resultEntity.setCallPrice(callPrice);
        resultEntity.setName(name);
    }

    @Override
    public boolean delete() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Id of Entity");
        int entityId = in.nextInt();
        TariffPlan resultEntity = _tariffPlan.getEntityByID(entityId);
        System.out.println(resultEntity);
        if (resultEntity == null) {
            System.out.println("Not found entity with "+ entityId + " Id");
            return false;
        }
        _tariffPlan.removeEntity(resultEntity);
        return true;
    }

    @Override
    public void add() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter Name :");
        String name = in.next();

        System.out.println("Enter Subscription Fee :");
        double subscriptionFee = in.nextDouble();

        System.out.println("Enter SMS price :");
        double smsPrice = in.nextDouble();
        System.out.println("Enter Call price :");
        double callPrice = in.nextDouble();

        int lengthOfCollection = _tariffPlan.getAmountOfEntities();
        TariffPlan lastTariffPlan = _tariffPlan.getEntityByPosition(lengthOfCollection - 1 );
        TariffPlan tariffPlan= new TariffPlan(lastTariffPlan == null? 0: lastTariffPlan.getId() + 1, name, subscriptionFee,Debit.everyMonth , smsPrice, callPrice  );

        _tariffPlan.addEntity(tariffPlan);
    }

    @Override
    public void saveChanges() {
        _tariffPlan.saveEntities();
    }
}
