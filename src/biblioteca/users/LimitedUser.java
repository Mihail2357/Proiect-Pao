package biblioteca.users;

import biblioteca.books.Book;

import java.util.ArrayList;

public class LimitedUser extends User{
    private int numberOfCredits;

    public LimitedUser(String name, String email, ArrayList<Book> books, int numberOfCredits) {
        super(name, email, books);
        this.numberOfCredits = numberOfCredits;
    }

    public LimitedUser(String name, String email, int numberOfCredits) {
        super(name, email);
        this.numberOfCredits = numberOfCredits;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }
}
