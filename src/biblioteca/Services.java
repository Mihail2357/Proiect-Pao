package biblioteca;

import biblioteca.books.Book;
import biblioteca.sections.Section;
import biblioteca.users.LimitedUser;
import biblioteca.users.PremiumUser;
import biblioteca.users.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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

    public static void addBook(Section section, Book book, int numberOfBooks){
        section.getBooks().put(book, numberOfBooks);

    }

    public static void printBooks(Set<Section> sections) {
        for(Section s: sections) {
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                System.out.println(entry.getKey());
        }
    }

    public static void printAutorBooks(Set<Section> sections, String autorName) {
        for (Section s : sections) {
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if (entry.getKey().getAutor().getName().equals(autorName))
                    System.out.println(entry.getKey());
        }
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
    }

    public static boolean checkBook(Set<Section> sections, String title) {
        for (Section s : sections)
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if (entry.getKey().getTitle().equals(title))
                    return true;
        return false;
    }

    public static void addUser(ArrayList<User> users, User user) {
        users.add(user);
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
    }

    public static void addCopies(Set<Section> sections, String title, int numberOfCopies) {
        for (Section s : sections)
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if(entry.getKey().getTitle().equals(title))
                    s.getBooks().replace(entry.getKey(), entry.getValue()+numberOfCopies);
    }

    public static void removeCopies(Set<Section> sections, String title, int numberOfCopies) {
        for (Section s : sections)
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if(entry.getKey().getTitle().equals(title))
                    s.getBooks().replace(entry.getKey(), entry.getValue()-numberOfCopies);
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

    }

}
