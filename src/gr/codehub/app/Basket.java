package gr.codehub.app;

import java.util.ArrayList;

public class Basket {

    public ArrayList<Product> getSelectedProducts() {
        return selectedProducts;
    }

    private ArrayList<Product> selectedProducts = new ArrayList<>();

    public void add(Product product){
        selectedProducts.add(product);
    }

    public void remove(Product product){
        selectedProducts.remove(product);
    }

    public void clear () {
        selectedProducts.clear();
    }

    public void display (){
        if(selectedProducts.isEmpty()){
            System.out.println("Your basket is empty");
        }else {
            int countP = 0;
            for (Product p : selectedProducts) {
                System.out.println("Product Number      Product Name        Product Price");
                System.out.println(countP + ".                   " + p.getName() + "                   " + p.getPrice());
                countP++;

            }
        }
    }

    public float getTotalCost(){

        Float sum = selectedProducts.stream()
                .map(x -> x.getPrice())
                .reduce(0.0F, Float::sum);

        return sum;
    }
}
