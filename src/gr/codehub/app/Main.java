package gr.codehub.app;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Shortcut for main method psvm
    public static void main(String[] args) {

        Customer customer = new Customer("George","Chatzopoulos","Galatsiou","ghatzopoulos@aueb.gr");
        ArrayList<Product> productsAvailable = new ArrayList<>();

        initializer(productsAvailable);
        System.out.println(customer.getName());
        System.out.println("Welcome to our Store!");
        System.out.println("View our menu");
        while(true){
            System.out.println("=========================");
            System.out.println("1. Add product to basket");
            System.out.println("2. Remove product from basket");
            System.out.println("3. Sum products");
            System.out.println("4. Exit");
            System.out.println("5. Display basket");
            System.out.println("=========================");
            System.out.println("Give your input right below: ");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            switch(input){
                case 1:
                    System.out.println("Select from product list: ");
                    int ans = printShoppingList(productsAvailable);
                    if(ans==-1){
                        System.exit(0);
                    }else{

                        if(productsAvailable.get(ans).getState() == State.NOT_AVAILABLE){
                            System.out.println("Product not available!");
                            break;
                        }

                        productsAvailable.get(ans).decreaseQuantity();
                        customer.addToBasket(productsAvailable.get(ans));
                        System.out.println("Purchase successful!");

                    }
                    break;

                case 2:
                    System.out.println("Remove product from basket");
                    System.out.println("Select product from your list");
                    customer.displayBasket();
                    Scanner sca = new Scanner(System.in);
                    int choice = sca.nextInt();
                    if(choice>customer.getBasket().getSelectedProducts().size() || choice < 0){
                        System.out.println("Wrong input!");
                    }else{
                        Product pr = customer.getBasket().getSelectedProducts().get(choice);
                        customer.removeFromBasket(pr);
                        for(int i=0; i< productsAvailable.size(); i++){
                            if(productsAvailable.get(i).getName().equals(pr.getName())){
                                System.out.println("-------------------------------------");
                                System.out.println(productsAvailable.get(i).getName());
                                System.out.println(pr.getName());
                                System.out.println("-------------------------------------");
                                productsAvailable.get(i).increaseQuantity();
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Proceed to checkout");
                    float price = customer.getBasket().getTotalCost();
                    System.out.println("Cost: " + price);
                    break;
                case 4:
                    System.exit(0);

                case 5:
                    System.out.println("Display basket");
                    customer.getBasket().display();
                    break;
            }

        }

    }

    public static void initializer(ArrayList<Product> productsAvailable){
        //Product List Initialization
        Product p1 = new Product("A12","Potato",2.4f,2,State.AVAILABLE);
        Product p2 = new Product("A13","Milk",1.28f,10,State.AVAILABLE);
        productsAvailable.add(p1);
        productsAvailable.add(p2);

    }

    public static int printShoppingList(ArrayList<Product> productsAvailable){
        System.out.println("\nProduct Number      Product Name        Product Price       Product Quantity        Product Available");
        for(int i=0; i<productsAvailable.size(); i++){
            System.out.println(i + ".                   " + productsAvailable.get(i).getName() + "                   " + productsAvailable.get(i).getPrice() + "                 " + (productsAvailable.get(i).getQuantity()) +  "                   " + productsAvailable.get(i).getState());
        }
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        boolean ans = selectInput(productsAvailable.size(), 0, input);
        if(ans){
            return input;
        }else{
            return -1;
        }

    }

    public static boolean selectInput(int up, int down, int input){
        if(input>up || input<down)
            return false;
        else
            return true;
    }

}
