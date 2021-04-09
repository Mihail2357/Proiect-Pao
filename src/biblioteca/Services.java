package biblioteca;

import biblioteca.books.Book;
import biblioteca.sections.Section;
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
                "6.Puteti crea un cont nou \n"+
                "7.\n"+
                "8. \n"+
                "9. \n"+
                "10. \n"+
                "11.\n"+
                "12.\n"

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
        for (Section s : sections) {
            for (Map.Entry<Book, Integer> entry : s.getBooks().entrySet())
                if (entry.getKey().getTitle().equals(title))
                    return true;
        }
        return false;
    }

    public static void addUser(ArrayList<User> users, User user) {
        users.add(user);

    }



}
