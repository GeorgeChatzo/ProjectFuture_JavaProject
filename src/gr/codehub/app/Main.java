package gr.codehub.app;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Shortcut for main method psvm
    public static void main(String[] args) {
        Ui ui = new Ui();
        Store store = new Store();
        ui.manageStore(store);
    }

}
