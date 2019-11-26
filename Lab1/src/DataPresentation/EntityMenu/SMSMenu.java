package DataPresentation.EntityMenu;

import BusinessLayer.Services.SMSService;
import DataAccess.Models.SMS;

import java.util.Scanner;

public class SMSMenu extends BaseMenu{

    private SMSService _smsService = new SMSService();

    @Override
    public void show() {
        int choice;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("----------Menu----------");
            System.out.println("1. Show sms sorted by Date");
            System.out.println("2. Show sms sorted by Id");
            System.out.println("3. Show sms sorted by Source");
            System.out.println("4. Show sms sorted by Text Size");
            System.out.println("Enter you choice");
            choice = in.nextInt();
        } while (choice > 4 || choice < 0 );
        switch (choice){
            case 1:
                _smsService.showSmsSortedByDate();
                break;
            case 2:
                _smsService.showSmsSortedById();
                break;
            case 3:
                _smsService.showSmsSortedBySource();
                break;
            case 4:
                _smsService.showSmsSortedByTextSize();
                break;
        }
        return;
    }

    @Override
    public void update() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Id of Entity");
        int entityId = in.nextInt();
        SMS resultEntity = _smsService.getEntityByID(entityId);
        System.out.println(resultEntity);
        if (resultEntity == null) {
            System.out.println("Not found entity with "+ entityId + " Id");
            return;
        }

        System.out.println("Enter Source :");
        int source = in.nextInt();
        System.out.println("Enter Destination :");
        int destination = in.nextInt();
        System.out.println("Enter Size of sms :");
        int size = in.nextInt();

        resultEntity.setTextSize(size);
        resultEntity.setDestinationClientId(destination);
        resultEntity.setSourceClientId(source);

    }

    @Override
    public boolean delete() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Id of Entity");
        int entityId = in.nextInt();
        SMS resultEntity = _smsService.getEntityByID(entityId);
        System.out.println(resultEntity);
        if (resultEntity == null) {
            System.out.println("Not found entity with "+ entityId + " Id");
            return false;
        }
        _smsService.removeEntity(resultEntity);
        return true;
    }

    @Override
    public void add() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter Source :");
        int source = in.nextInt();
        System.out.println("Enter Destination :");
        int destination = in.nextInt();
        System.out.println("Enter Size of sms :");
        int size = in.nextInt();

        int lengthOfCollection = _smsService.getAmountOfEntities();
        SMS lastSms = _smsService.getEntityByPosition(lengthOfCollection - 1 );

        SMS sms= new SMS(lastSms == null? 0 : lastSms.getServiceId() + 1 , source, destination, size );

        _smsService.addEntity(sms);
    }

    @Override
    public void saveChanges() {
        _smsService.saveEntities();
    }
}
