package DataPresentation.EntityMenu;

import BusinessLayer.Services.CallService;
import DataAccess.Models.Call;

import java.sql.Time;
import java.util.Scanner;

public class CallMenu extends BaseMenu{


    private CallService _callService = new CallService();
    @Override
    public void show()  {
        int choice;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("----------Menu----------");
            System.out.println("1. Show Call sorted by Id");
            System.out.println("2. Show Call sorted by Date");
            System.out.println("3. Show Call sorted by Source");
            System.out.println("4. Show Call sorted by Duration");
            System.out.println("Enter you choice");
            choice = in.nextInt();
        } while (choice > 4 || choice < 0 );
        switch (choice){
            case 1:
                _callService.showCallSortedById();
                break;
            case 2:
                _callService.showCallSortedByDate();
                break;
            case 3:
                _callService.showCallSortedBySource();
                break;
            case 4:
                _callService.showCallSortedByDuration();
                break;
        }
        return;
    }

    @Override
    public void update() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Id of Entity");
        int entityId = in.nextInt();
        Call resultEntity = _callService.getEntityByID(entityId);
        System.out.println(resultEntity);
        if (resultEntity == null) {
            System.out.println("Not found entity with "+ entityId + " Id");
            return;
        }

        System.out.println("Enter source Id :");
        int source = in.nextInt();
        System.out.println("Enter destination Id :");
        int destination = in.nextInt();
        System.out.println("Enter duration :");
        Time duration = new Time( in.nextLong());

        resultEntity.setDuration(duration);
        resultEntity.setSourceClientId(source);
        resultEntity.setDestinationClientId(destination);
    }

    @Override
    public boolean delete() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Id of Entity");
        int entityId = in.nextInt();
        Call resultEntity = _callService.getEntityByID(entityId);
        System.out.println(resultEntity);
        if (resultEntity == null) {
            System.out.println("Not found entity with "+ entityId + " Id");
            return false;
        }
        _callService.removeEntity(resultEntity);
        return true;
    }

    @Override
    public void add() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter source Id :");
        int source = in.nextInt();
        System.out.println("Enter destination Id :");
        int destination = in.nextInt();
        System.out.println("Enter duration :");
        Time duration = new Time( in.nextLong());

        int lengthOfCollection = _callService.getAmountOfEntities();
        Call lastCall = _callService.getEntityByPosition( lengthOfCollection - 1 );


        Call call= new Call(source,destination, duration, lastCall == null? 0: lastCall.getServiceId() + 1);

        _callService.addEntity(call);


    }

    @Override
    public void saveChanges() {
        _callService.saveEntities();
    }
}
