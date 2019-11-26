import DataPresentation.EntityMenu.BaseMenu;
import DataPresentation.Menu;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while (true) {
            BaseMenu chosenEntityMenu = Menu.ChooseEntity();
            if (chosenEntityMenu == null)
                return;
            Menu.ExecuteChosenAction(chosenEntityMenu);

        }
    }
}
