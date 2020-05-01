package gr.codehub.app;

//Store Class

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Store {

    private String name;
    private int storeCode;
    private String Address;
    private ArrayList<Customer> registeredCustomers = new ArrayList<>();
    private ArrayList<Product> availableProducts = new ArrayList<>();

    public Store(String name,int storeCode,String Address){
        this.name = name;
        this.storeCode = storeCode;
        this.Address = Address;
    }

    public Store() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public ArrayList<Customer> getRegisteredCustomers() {
        return registeredCustomers;
    }

    public ArrayList<Product> getAvailableProducts() {
        return availableProducts;
    }

    //Load customer List already registered to the store
    public void loadCustomerList(){
        Scanner sc = null;
        try {
            sc = new Scanner(new File("C:\\Users\\GeorgeChatz\\Desktop\\JavaFundamentals\\src\\gr\\codehub\\app\\Customers"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] words = line.split(",");
            if(words.length !=5 ){
                System.out.println("Cannot create new customer");
            }else{
                Customer c = new Customer(words[0],words[1],words[2],words[3],words[4]);
                registeredCustomers.add(c);
            }
        }
    }

    //load already available products
    public void loadProductList(){
        Scanner sc = null;
        try {
            sc = new Scanner(new File("C:\\Users\\GeorgeChatz\\Desktop\\JavaFundamentals\\src\\gr\\codehub\\app\\Products"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] words = line.split(",");
            if(words.length !=4 ){
                System.out.println("Cannot create new product");
            }else{
                Product p = new Product(words[0],words[1],Float.parseFloat(words[2]),Integer.parseInt(words[3]));
                availableProducts.add(p);
            }
        }

    }


    //Add new product to available list
    public void addProductToList(Product p){
        availableProducts.add(p);
    }

    //Add new customer to Customer List
    public void addCustomerToList(Customer c){
        registeredCustomers.add(c);
    }

    public void displayCustomerList(){
        registeredCustomers.forEach(System.out::println);
    }

    public void displayProductList(){
        availableProducts.forEach(System.out::println);
    }

    //Export Customer List updated if a new customer is added
    public void exportCustomerList(String filename){
        try {
            PrintWriter printWriter = new PrintWriter(new File(filename));
            for (Customer c : registeredCustomers) {
                printWriter.println(c.getName() + "," + c.getSurname() + "," + c.getAddress()
                        + "," + c.getEmail() + "," + c.getPassword());
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file cannot be saved");
        }
    }

    //Export Product List available after a new product is added
    public void exportProductList(){

    }

    public void removeCustomer(int index){
        registeredCustomers.remove(index);
    }

    public Customer login(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.next();
        System.out.println("Enter your password: ");
        String password = sc.next();
        Customer customer = null;
        for (Customer registeredCustomer : registeredCustomers) {
            if (registeredCustomer.getName().equals(name) && registeredCustomer.getPassword().equals(password)) {
                customer = registeredCustomer;
                System.out.println("User successfully found!");
                return customer;
            }
        }
        return customer;
    }

    public Customer registerUser(){
        System.out.println("Starting register process...");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = sc.next();
        System.out.println("Enter surname: ");
        String surname = sc.next();
        System.out.println("Enter address: ");
        String address = sc.next();
        System.out.println("Enter email: ");
        String email = sc.next();
        System.out.println("Give password: ");
        String password = sc.next();
        Customer c = new Customer(name,surname,address,email,password);
        System.out.println("Loading....50%");
        System.out.println("Loading....100%");
        System.out.println("Registration completed!");
        registeredCustomers.add(c);

        return c;
    }


}
