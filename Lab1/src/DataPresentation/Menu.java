package DataPresentation;

import DataPresentation.EntityMenu.*;

import java.util.Scanner;

public class Menu {

    public static int CRUDMenu(){
        int choice;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("----------Menu----------");
            System.out.println("1. Show Entities");
            System.out.println("2. Add Entities");
            System.out.println("3. Delete Entity");
            System.out.println("4. Update Entity");
            System.out.println("5. Save changes");
            System.out.println("6. To entity menu");
            System.out.println("Enter you choice");
            choice = in.nextInt();
        } while (choice > 6 || choice < 0 );
        return choice;
    }

    public static BaseMenu ChooseEntity(){
        int choice;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("----------Menu----------");
            System.out.println("Please, choose entity");
            System.out.println("1. Client");
            System.out.println("2. Company");
            System.out.println("3. Tariff Plan");
            System.out.println("4. Sms");
            System.out.println("5. Call");
            System.out.println("6. Exit");
            System.out.println("Enter you choice");
            choice = in.nextInt();
        } while (choice > 6 || choice < 0 );

        switch (choice){
            case 1: return new ClientMenu();
            case 2: return new CompanyMenu();
            case 3: return new TariffPlanMenu();
            case 4: return new SMSMenu();
            case 5: return new CallMenu();
        }
        return null;
    }

    public  static void ExecuteChosenAction(BaseMenu chosenEntityMenu){
        while (true) {
            int crudChoice = Menu.CRUDMenu();
            switch (crudChoice) {
                case 1: {
                    chosenEntityMenu.show();
                    break;
                }
                case 2: {
                    chosenEntityMenu.add();
                    break;
                }
                case 3: {
                    chosenEntityMenu.delete();
                    break;
                }
                case 4: {
                    chosenEntityMenu.update();
                    break;
                }
                case 5:{
                    chosenEntityMenu.saveChanges();
                    break;
                }
                case 6:{
                    return;
                }
            }
        }

    }
}
