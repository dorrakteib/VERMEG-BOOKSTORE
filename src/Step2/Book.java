package Step2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private Date releaseDate;

    public Book(int id, String title, String author, double price, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public Book(String title, String author, double price, Date releaseDate) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(releaseDate);
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Book n°" + id +
                ":\n\ttitle: " + title +
                "\n\tauthor: " + author +
                "\n\tprice: " + price +
                "\n\trelease date: " + releaseDate;
    }
}
