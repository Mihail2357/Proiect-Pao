package biblioteca;

import biblioteca.books.AudioBook;
import biblioteca.books.Book;
import biblioteca.books.PaperBook;
import biblioteca.sections.Section;
import biblioteca.users.LimitedUser;
import biblioteca.users.PremiumUser;
import biblioteca.users.User;

import java.text.SimpleDateFormat;
import java.util.*;

public class Services {
    public static void display()
    {
        System.out.println("BUNA ZIUA SI BUN VENIT IN APLICATIA NOASTRA!" +
                "  Puteti face urmatoarele operatii \n\n" +
                "1.Puteti adauga o carte intr-o sectiune\n"+
                "2.Puteti afisa toate cartile din biblioteca \n"+
                "3.Puteti afisa toate cartile scrise de un anumit autor\n"+
                "4.Puteti verifica cate carti cu un anumit titlu sunt in stoc\n"+
                "5.Puteti inchiria o carte\n"+
                "6.Puteti crea un user nou \n"+
                "7.Puteti afisa titlul cartii cu cele mai multe exemplare\n"+
                "8.Puteti mari numarul de exemplare a unei carti  \n"+
                "9.Puteti micsora numarul de exemplare a unei carti\n"+
                "10.Puteti sa returnati o carte \n"

        );
    }

    public static List<Book> readBooks() {
        return Singleton.getInstance().readBooks("Books.csv");
    }

    public static void addBooksFromCSV(Section s, List<Book> books ){
        for(Book b : books)
            addBook(s, b,1);
    }

    public static void writeBooksInCSV(Set<Section>sections) {
        for(Section s :sections) {
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet()) {
                writeBookInCSV(entry.getKey(), entry.getValue());
                insertBook(entry.getKey(), entry.getValue(), s.getName());
            }
        }

    }

    public static void writeBookInCSV(Book book, Integer nr) {

        if (book instanceof AudioBook) {
            String[] data = {book.getTitle(), book.getAutor().getName(), book.getSection(), book.getReleaseDate(),  Integer.toString(((AudioBook) book).getNumberOfMinutes()), ((AudioBook) book).getNarator().getName(), String.valueOf(nr)};
            Singleton.getInstance().writeInCsv("AudioBooks.csv", data);
        }
        if (book instanceof PaperBook) {
            String[] data = {book.getTitle(), book.getAutor().getName(), book.getSection(), book.getReleaseDate(),  ((PaperBook) book).getPublisher(), Integer.toString(((PaperBook) book).getNumberOfPages()), String.valueOf(nr)};
            Singleton.getInstance().writeInCsv("PaperBooks.csv", data);
        }
    }

    public static List<User> readUsers() {
        return Singleton.getInstance().readUsers("Users.csv");
    }

    public static void addUsersFromCSV(ArrayList<User> users, List<User> u ) {
        for(User user : u)
            addUser(users, user);
    }

    public static void writeUsersInCSV(List<User>users) {
        for(User user : users) {
                writeUserInCSV(user);
        }
    }

    public static void writeUserInCSV(User user) {

        if (user instanceof LimitedUser) {
            String[] data = {user.getName(), user.getEmail(), String.valueOf(((LimitedUser) user).getNumberOfCredits())};
            Singleton.getInstance().writeInCsv("LimitedUser.csv", data);
        }
        if (user instanceof PremiumUser) {
            String[] data = {user.getName(), user.getEmail(), String.valueOf(((PremiumUser) user).getDaysOfSubscription())};
            Singleton.getInstance().writeInCsv("PremiumUser.csv", data);
        }
    }

    public static void actionWrite(String name){
        String [] data = {name, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date())};
        Singleton.getInstance().writeInCsv("ActionsWrite.csv", data);
    }


    public static void addBook(Section section, Book book, int quantity){
        section.getBooks().put(book, quantity);
        writeBookInCSV(book, quantity);
        insertBook(book, quantity, section.getName());
        actionWrite("addBook");
    }

    public static void printBooks(Set<Section> sections) {
        for(Section s: sections) {
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                System.out.println(entry.getKey());
        }
        actionWrite("printBooks");
    }

    public static void printAutorBooks(Set<Section> sections, String autorName) {
        for (Section s : sections) {
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if (entry.getKey().getAutor().getName().equals(autorName))
                    System.out.println(entry.getKey());
        }
        actionWrite("printAutorBooks");
    }
    public static void printNumberOfBooks(Set<Section> sections, String title) {
        boolean found=false;
        for (Section s : sections) {
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if (entry.getKey().getTitle().equals(title)) {
                    System.out.println(title+" " + entry.getValue());
                    found=true;
                }
        }
        if(!found)
            System.out.println(title+" 0");
        actionWrite("printNumberOfBooks");
    }

    public static boolean checkBook(Set<Section> sections, String title) {
        actionWrite("checkBook");
        for (Section s : sections)
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if (entry.getKey().getTitle().equals(title))
                    return true;
        return false;
    }

    public static void addUser(ArrayList<User> users, User user) {
        users.add(user);
        writeUserInCSV(user);
        actionWrite("addUser");
    }

    public static void rentBook(Set<Section> sections, ArrayList<User> users, String name, String title) {
        for (User u : users)
            if (u.getName().equals(name) && (u instanceof PremiumUser || ((LimitedUser) u).getNumberOfCredits() > 0)) {
                for (Section s : sections)
                    for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                        if (entry.getKey().getTitle().equals(title) && entry.getValue() > 0) {
                            Map<Book, Integer> map = s.getBooks();
                            map.replace(entry.getKey(), entry.getValue() - 1);
                            s.setBooks(map);
                            u.getBooks().add(entry.getKey());
                            if (u instanceof LimitedUser)
                                ((LimitedUser) u).setNumberOfCredits(0);
                            System.out.println("Carte inchiriata");
                        }
            }
        actionWrite("rentBook");
    }

    public static void mostCopies(Set<Section> sections) {
        int numberOfCopies = 0;
        for (Section s : sections)
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if(entry.getValue() > numberOfCopies)
                    numberOfCopies = entry.getValue();
        for (Section s : sections)
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if(entry.getValue() == numberOfCopies)
                    System.out.println(entry.getKey().getTitle() + " " + entry.getValue());
        actionWrite("mostCopiesBook");
    }

    public static void addCopies(Set<Section> sections, String title, int numberOfCopies) {
        for (Section s : sections)
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if(entry.getKey().getTitle().equals(title))
                    s.getBooks().replace(entry.getKey(), entry.getValue()+numberOfCopies);
        actionWrite("addCopies");
    }

    public static void removeCopies(Set<Section> sections, String title, int numberOfCopies) {
        for (Section s : sections)
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if(entry.getKey().getTitle().equals(title))
                    s.getBooks().replace(entry.getKey(), entry.getValue()-numberOfCopies);
        actionWrite("removeCopies");
    }

    public static void returnBook(Set<Section> sections, ArrayList<User> users, String name, String title) {
        for (User u : users)
            if (u.getName().equals(name)) {
                u.getBooks().removeIf(n -> n.getTitle().equals(title));
                for (Section s : sections)
                    for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                        if(entry.getKey().getTitle().equals(title)) {
                            Map<Book, Integer> map = s.getBooks();
                            map.replace(entry.getKey(), entry.getValue() - 1);
                            s.setBooks(map);
                            System.out.println("Cartea a fost returnata");
                        }
            }
        actionWrite("returnBook");
    }

    //etapa3
    public static void insertBook(Book book, int quantity, String section) {
       if(book instanceof PaperBook) {
           String[] data = {book.getTitle(), book.getAutor().getName(), book.getSection(), book.getReleaseDate(), ((PaperBook) book).getPublisher(), Integer.toString(((PaperBook) book).getNumberOfPages())};
           Database.getDatabaseInstance().insertBook(data,"paper_books");
       }
        if(book instanceof AudioBook) {
            String[] data = {book.getTitle(), book.getAutor().getName(), book.getSection(), book.getReleaseDate(), Integer.toString(((AudioBook) book).getNumberOfMinutes()), ((AudioBook) book).getNarator().getName()};
            Database.getDatabaseInstance().insertBook(data,"audio_books");
        }
    }

}
