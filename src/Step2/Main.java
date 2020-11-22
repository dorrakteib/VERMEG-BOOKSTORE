package Step2;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException, SQLException {
        System.out.println("----------------------Welcome to our " +
                "bookstore-----------------------\n\n");
        int choice = 0;
        BooksManager bm = new BooksManager();
        do {
            System.out.println("****************************Menu*******************************");
            System.out.println("Please choose what to do");
            System.out.println("1: Add a new book");
            System.out.println("2: Display all the saved books");
            System.out.println("3: Delete a book");
            System.out.println("4: Update a book's data");
            System.out.println("5: Exit");
            Scanner intSc = new Scanner(System.in);
            choice = intSc.nextInt();
            switch (choice) {
                case 1: {
                    Scanner strSc = new Scanner(System.in);
                    System.out.println("Please enter the book's title");
                    String title = strSc.nextLine();

                    System.out.println("Please enter the book's author");
                    String auth = strSc.nextLine();

                    Scanner doubleSc = new Scanner(System.in);
                    System.out.println("Please enter the book's price");
                    double price = doubleSc.nextDouble();

                    System.out.println("Please enter the book's release date");
                    System.out.println("A valid date must be in this form: yyyy-MM-dd");
                    // Formating the date
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date release = format.parse(strSc.nextLine());

                    Book b = new Book(title, auth, price, release);

                    // Saving the new book in the database
                    bm.addBook(b);
                    break;
                }
                case 2: {
                    bm.displayAllBooks();
                    break;
                }
                case 3: {
                    System.out.println("Please enter the ID of the book you want to delete");
                    int id = intSc.nextInt();
                    bm.deleteBook(id);
                    break;
                }
                case 4: {
                    System.out.println("Please enter the ID of the book you want to update its " +
                            "data");
                    int id = intSc.nextInt();
                    bm.updateBook(id);
                    break;
                }
                case 5: {
                    System.out.println("Good bye! See you soon");
                    bm.c.close();
                }
            }
        } while (choice != 5);
    }
}
