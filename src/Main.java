import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("**************************** Welcome to out store ***************************\n");

        try {
            //Getting the user input(unit price & quantity)
            System.out.print("Please enter the product's unit price \t");
            double price = sc.nextDouble();
            // if the entred price is invalid an error message will be shown
            if (price<=0 ) System.out.println("Invalid price please verify then run the program again");
            else {
                System.out.print("Please enter the product's quantity \t");
                int qty = sc.nextInt();

                // if the entred quantity is invalid an error message will be shown
                if (qty<=0 ) System.out.println("Invalid quantity please verify then run the program again");
                else {
                    Purchase p = new Purchase(qty, price);
                    // the output: the total price
                    System.out.println(p.toString());
                    System.out.println("_____________________________________");
                    System.out.println("\tTotal price: " + p.calculateTotalPrice() + " dt\n");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input value");
        }
    }
}