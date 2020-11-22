package Step2;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class BooksManager {
    Connection c = (new Connexion()).connect();

    public ResultSet searchBookByTitle(String title) {
        ResultSet res = null;
        try {
            String req = "SELECT * FROM books WHERE title = ?";
            PreparedStatement ps = c.prepareStatement(req);
            ps.setString(1, title);
            res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet searchBookById(int id) {
        ResultSet res = null;
        try {
            String req = "SELECT * FROM books WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(req);
            ps.setInt(1, id);
            res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void addBook(Book b) {
        try {
            if (searchBookByTitle(b.getTitle()).next())
                System.out.println("This book already exists");
            else {
                String req = "INSERT INTO books (title,author,price,releaseDate) VALUES (?,?,?,?)";
                int res = -1;

                PreparedStatement ps = c.prepareStatement(req);
                ps.setString(1, b.getTitle());
                ps.setString(2, b.getAuthor());
                ps.setDouble(3, b.getPrice());
                ps.setDate(4, Date.valueOf(b.getReleaseDate()));
                res = ps.executeUpdate();
                ps.close();

                if (res != -1) {
                    System.out.println("Book added successfully");
                } else {
                    System.out
                            .println("An error occured while adding the new book to the database");
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occured while adding the new book to the database");
            e.printStackTrace();
        }
    }

    public void displayAllBooks() {
        try {
            String req = "SELECT id, rpad(title,20,' '), rpad(author,20,' '), price,releaseDate " +
                    "FROM books";
            Statement ps = c.createStatement();
            ResultSet res = ps.executeQuery(req);
            System.out.println("id\t\tTitle\t\t\t\t\t\tAuthor\t\t\t\t\t\tPrice (dt)\t\t\t\t" +
                    "Release date");
            while (res.next()) {
                System.out.println(res.getString(1) + "\t\t" + res
                        .getString(2) + "\t\t" + res.getString(3) + "\t\t" + res
                        .getDouble(4) + "\t\t\t\t\t" + res.getString(5));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        try {
            if (searchBookById(id).next()){
                String req = "DELETE FROM books WHERE id = ?";
                PreparedStatement ps = c.prepareStatement(req);
                ps.setInt(1, id);
                if (ps.executeUpdate()==-1)
                    System.out.println("An error occured we couldn't delete this book ");
                else
                    System.out.println("Book deleted successfully");
                ps.close();
            }
            else
                System.out.println("None of the books have the specified  ID: "+ id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(int id) {
        try {
            ResultSet res = searchBookById(id);
            if (res.next()){
                Scanner sc  = new Scanner(System.in);
                Book b = new Book(res.getInt("id"), res.getString("title"),
                        res.getString("author"), res.getDouble("price"), res.getDate("releaseDate"));

                System.out.println("Would you like to update the book's title? \n 0: no \t 1: " +
                        "yes");
                int choice = sc.nextInt();
                Scanner strSc = new Scanner(System.in);
                switch (choice){

                    case 1: {
                        System.out.println("Please enter the new title");
                        b.setTitle(strSc.nextLine());
                    }
                    case 0: {
                        System.out.println("Would you like to update the book's author? \n 0: no " +
                                "\t 1: yes");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1: {
                                System.out.println("Please enter the new author");
                                b.setAuthor(strSc.nextLine());
                            }
                            case 0: {
                                System.out
                                        .println("Would you like to update the book's price? \n " +
                                                "0: no " +
                                                "\t 1: yes");
                                int chP = sc.nextInt();
                                switch (chP){
                                    case 1: {
                                        System.out.println("Please enter the new price");
                                        b.setPrice(sc.nextDouble());
                                    }
                                    case 0: {
                                        System.out
                                                .println("Would you like to update the book's " +
                                                        "release date? \n " +
                                                        "0: no " +
                                                        "\t 1: yes");
                                        int chD = sc.nextInt();
                                        switch (chD) {
                                            case 1: {
                                                System.out.println("Please enter the new release " +
                                                        "date");
                                                System.out.println("A valid date must be in this form: yyyy-MM-dd");
                                                b.setReleaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(strSc.nextLine()));
                                            }
                                            case 0: {
                                                String req = "UPDATE books SET title = ?, author = ?, price = ?, releaseDate=? " +
                                                        "WHERE id " +
                                                        "= ?";
                                                PreparedStatement ps = c.prepareStatement(req);
                                                ps.setString(1, b.getTitle());
                                                ps.setString(2, b.getAuthor());
                                                ps.setDouble(3, b.getPrice());
                                                ps.setDate(4, Date.valueOf(b.getReleaseDate()));
                                                ps.setInt(5, id);
                                                if (ps.executeUpdate()==-1)
                                                    System.out.println("An error occured we couldn't update this book ");
                                                else
                                                    System.out.println("Book's data updated successfully");
                                                ps.close();
                                                break;
                                            }
                                            default:
                                                System.out.println("Invalid Value");
                                        }
                                        break;
                                    }
                                    default:
                                        System.out.println("Invalid Value");
                                }
                                break;
                            }
                            default:
                                System.out.println("Invalid Value");
                        }
                        break;
                    }
                    default:
                        System.out.println("Invalid Value");
                }
            }
            else
                System.out.println("None of the books have the specified  ID: "+ id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
