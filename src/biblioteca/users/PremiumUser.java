package biblioteca.users;

import biblioteca.books.Book;

import java.util.ArrayList;

public class PremiumUser extends User{
    private int daysOfSubscription;

    public PremiumUser(String name, String email, ArrayList<Book> books, int daysOfSubscription) {
        super(name, email, books);
        this.daysOfSubscription = daysOfSubscription;
    }

    public PremiumUser(String name, String email, int daysOfSubscription) {
        super(name, email);
        this.daysOfSubscription = daysOfSubscription;
    }

    public int getDaysOfSubscription() {
        return daysOfSubscription;
    }

    public void setDaysOfSubscription(int daysOfSubscription) {
        this.daysOfSubscription = daysOfSubscription;
    }
}
