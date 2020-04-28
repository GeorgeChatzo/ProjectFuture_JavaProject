package gr.codehub.app;

import java.util.ArrayList;

public class Customer {

    private String name;
    private String surname;
    private String address;
    private int customerID;
    private String email;
    private static int nextID=1;
    //variable to connect 1to1 connection basket with Customer relationship
    private Basket basket;

    //Customer constructor with fields name,surname,address,email
    //we don't include customerID because system would incrementally add
    //customerID based on the previous one so that each customer is a unique object in our system.
    public Customer(String name, String surname, String address, String email) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        customerID = nextID++;
        this.basket = new Basket();
    }

    //default constructor
    public Customer(){
        this.name = "";
        this.surname = "";
        this.address = "";
        this.email = "";
        customerID = nextID++;
        this.basket = new Basket();
    }


    public void displayBasket(){
        basket.display();
    }

    public void addToBasket(Product p){
        this.basket.add(p);
    }

    public void removeFromBasket(Product p){
        this.basket.remove(p);
    }

    public Basket getBasket(){
        return basket;
    }


    //getters setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", customerID='" + customerID + '\'' +
                '}';
    }
}
