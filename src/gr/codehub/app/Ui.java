package gr.codehub.app;


//class to model our menu and all options we have in our shop
//Class to connect menu with some basic functionality
//InitialMenu: Login with user credential

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    ArrayList<Customer> customers = new ArrayList<>();
    static Customer customer = null;

    public Choice LoginMenu(){
        Choice returnChoice;
        System.out.println("***   WELCOME TO OUR STORE   ***");
        System.out.println("Select option: ");
        System.out.println("1.Login     " +
                           "2. Register");

        int choice;
        Scanner scanner = new Scanner(System.in);
        try {
            choice = scanner.nextInt();
            switch(choice){
                case 1: return Choice.LOGIN;
                case 2: return Choice.REGISTER;
                default:return Choice.ERROR;
            }
        }
        catch(Exception e){
            return Choice.ERROR;
        }

    }

    public Choice MainMenu(){

        System.out.println("***   MAIN MENU   ***");
        System.out.println("Go Shopping!");
        System.out.println("1. Add product to basket");
        System.out.println("2. Remove product from basket");
        System.out.println("3. Display all available products");
        System.out.println("4. Show basket");
        System.out.println("5. Calculate basket cost");
        System.out.println("6. Clear basket");
        System.out.println("7. Logged in user information");
        //System.out.println("8. Add customer");
        //System.out.println("9. See customer list");
        //System.out.println("10. Load Customer list");
        //System.out.println("11. Save Customer list");

        int choice;
        Scanner scanner = new Scanner(System.in);
        try {
            choice = scanner.nextInt();
            switch(choice){
                case 1: return Choice.ADD;
                case 2: return Choice.REMOVE;
                case 3: return Choice.DISPLAY_PROD;
                case 4: return Choice.DISPLAY_BASK;
                case 5: return Choice.TOTALCOST;
                case 6: return Choice.CLEAR;
                case 7: return Choice.WHOAMI;
                case 8: return Choice.ADD_CUSTOMER;
                case 9: return Choice.DISPLAY_CUSTOMER;
                case 10: return Choice.LOAD_CUSTOMER;
                case 11: return Choice.SAVE_CUSTOMER;
                case 0: return Choice.EXIT;
                default:return Choice.ERROR;

            }
        }
        catch(Exception e){
            return Choice.ERROR;
        }

    }

    public void manageStore(Store store){
        store.loadCustomerList();
        store.loadProductList();
        Choice choice;
        //result from login menu
        choice = LoginMenu();

        //login menu choice , given store
        manageCustomer(choice,store);
        manageChoice(store);

    }

    //factory method design pattern
    public Product createProduct(){
        String code;
        String name;
        float price;
        int quantity;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Give the product code ");
        code = scanner.next();
        System.out.println("Give the product name ");
        name = scanner.next();
        System.out.println("Give the product price ");
        price = scanner.nextFloat();
        System.out.println("Give the product quantity ");
        quantity = scanner.nextInt();

        Product product = new Product(code, name, price, quantity);

        return product;

    }

    //Initialize product list by passing product parameters
    public void initlizerFromUser(){
        Product product = createProduct();

    }

    public void manageCustomer(Choice ch,Store store){
        switch(ch) {
            case REGISTER:
                System.out.println("Register choice selected");
                customer = store.registerUser();
                ch = Choice.EXIT;
                break;
            case LOGIN:
                System.out.println("Logging in....");
                customer = store.login();
                ch = Choice.EXIT;
                break;
        }
    }



    public void manageChoice(Store store){

        Choice choice;

        do {
            choice = MainMenu();
            switch (choice) {
                case ADD:
                    Product product =  createProduct();
                    customer.getBasket().addProduct(product);
                    break;
                case REMOVE:
                    System.out.println("Give an index to remove");
                    Scanner scanner = new Scanner(System.in);
                    int index= scanner.nextInt();
                    customer.getBasket().removeProduct(index);
                    break;
                case DISPLAY_BASK:
                    customer.getBasket().displayProducts();
                    break;
                case CLEAR:
                    customer.getBasket().clearProducts();
                    break;
                case TOTALCOST:
                    System.out.println("TotalCost= " + customer.getBasket().getTotalCost());
                    break;
                case EXIT:
                    System.exit(1);
                    break;
                case SAVE:
                    customer.getBasket().saveBasket("basket.txt");
                    break;
                case LOAD:
                    customer.getBasket().loadBasket("basket.txt");
                    break;
                case DISPLAY_PROD:
                    store.displayProductList();
                    break;
                case ERROR:
                    System.out.println("You gave erroneous input");
                    break;
                case WHOAMI:
                    System.out.println("User info");
                    System.out.println(customer.getName());
                    break;
                case ADD_CUSTOMER:
                    System.out.println("Register new customer");
                    store.registerUser();
                    break;
                case REMOVE_CUSTOMER:
                    store.displayCustomerList();
                    Scanner sc = new Scanner(System.in);
                    int pos = sc.nextInt();
                    store.removeCustomer(pos);
                    break;
                case DISPLAY_CUSTOMER:
                    store.displayCustomerList();
                    break;
                case SAVE_CUSTOMER:
                    store.exportCustomerList("newCustomerFile.txt");
                    break;
                case LOAD_CUSTOMER:
                    store.loadCustomerList();
                    break;
            }

            Scanner sc = new Scanner(System.in);
            choice = MainMenu();


        }while(choice!=Choice.EXIT);

    }


}
