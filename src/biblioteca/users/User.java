package biblioteca.users;

import biblioteca.books.Book;

import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private ArrayList<Book> books = new ArrayList<>();

    public User(String name, String email, ArrayList<Book> books) {
        this.name = name;
        this.email = email;
        this.books = books;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
