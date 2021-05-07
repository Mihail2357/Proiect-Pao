package biblioteca;

import biblioteca.autors.Autor;
import biblioteca.autors.Narator;
import biblioteca.books.AudioBook;
import biblioteca.books.Book;
import biblioteca.books.PaperBook;
import biblioteca.sections.Section;
import biblioteca.users.LimitedUser;
import biblioteca.users.PremiumUser;
import biblioteca.users.User;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        LimitedUser user1 = new LimitedUser("Ana", "AA@gg.com", 1);
        users.add(user1);
        Narator narator1 = new Narator("David", "Narator subiectiv","Engleza");
        Autor autor1 = new Autor("Mihail", "Nobel economie 2009", "Economie");
        Book book1 = new PaperBook("aad", autor1, "Economie", "12.2.2009", "abc", 500);
        Book book2 = new AudioBook("aageh", autor1, "Economie", "12.2.2009", 1000, narator1);
        Map<Book, Integer> books = new HashMap<Book, Integer>();
        books.put(book1, 4);
        books.put(book2, 6);
        Section section1 = new Section("Economie", books);
        List<Section> se = new ArrayList<>();
        se.add(section1);
        Set<Section> sections = new TreeSet<>(se);

        Services.writeBooksInCSV(sections);
        Services.addBooksFromCSV(section1, Services.readBooks());
        Services.writeUsersInCSV(users);
        Services.addUsersFromCSV(users, Services.readUsers());

        int i;
        Scanner in = new Scanner(System.in);

        Services.display();
        System.out.println("Cate servicii doriti?");
        int nr_services = in.nextInt();

        for (i = 0; i < nr_services; i++) {
            System.out.println("Numarul serviciului dorit:");

            int number = in.nextInt();


            if (number == 1) {
                System.out.println("Care este tipul cartii? 1- paperBook, 2- audioBook ");
                int bookType =in.nextInt();
                System.out.println("Care este numarul de carti de acest tip adaugat?");
                int numberOfBooks=in.nextInt();
                System.out.println("Care este numele cartii?");
                String title = in.next();
                System.out.println("Care este numele autorului?");
                String autorName = in.next();
                System.out.println("Introduceti informatii despre autor");
                String autorInformation = in.next();
                System.out.println("Introduceti sectiunea autorului");
                String autorSection = in.next();
                Autor autor = new Autor(autorName, autorInformation, autorSection);
                System.out.println("Care este sectiunea cartii?");
                String bookSection = in.next();
                System.out.println("Care este data de aparitie a cartii?");
                String bookDate = in.next();

                if(bookType == 1 ){
                    System.out.println("Care este editura?");
                    String publisher=in.next();
                    System.out.println("Care este numarul de pagini?");
                    int numberOfPages=in.nextInt();
                    for(Section s: sections)
                        if(s.getName().equals(bookSection))
                            Services.addBook(s, new PaperBook(title, autor, bookSection, bookDate, publisher, numberOfPages), numberOfBooks);
                }

                if(bookType == 2 ){
                    System.out.println("Care este numarul de minute?");
                    int numberOfMinutes=in.nextInt();
                    System.out.println("Care este numele naratorului?");
                    String naratorName=in.next();
                    System.out.println("Introduceti informatii despre narator");
                    String naratorInformation = in.next();
                    System.out.println("Introduceti limba in care naratorul nareaza:");
                    String naratorlanguage = in.next();
                    Narator narator = new Narator(naratorName, naratorInformation, naratorlanguage);
                    for(Section s: sections)
                        if(s.getName().equals(bookSection))
                            Services.addBook(s, new AudioBook(title, autor, bookSection, bookDate, numberOfMinutes,narator), numberOfBooks);
                }
            }

            if (number == 2) {
                Services.printBooks(sections);
            }

            if(number == 3){
                System.out.println("Care este numele autorului?");
                String autorName=in.next();
                Services.printAutorBooks(sections, autorName);
            }

            if(number == 4){
                System.out.println("Care este titlul cartii?");
                String title=in.next();
                Services.printNumberOfBooks(sections, title);
            }

            if(number == 5) {
                System.out.println("Care este numele dumneavoastra??");
                String name = in.next();
                System.out.println("Care este titlul cartii pe care vreti sa o inchiriati?");
                String title = in.next();
                Services.rentBook(sections, users, name, title);
            }

            if(number == 6) {
                System.out.println("Care este numele dumneavoastra??");
                String name = in.next();
                System.out.println("Care este emailul dumneavoastra??");
                String email = in.next();
                System.out.println("Vreti un abonament gratis(1) sau unul premium?(2)");
                int type = in.nextInt();
                if (type == 1)
                    Services.addUser(users, new LimitedUser(name, email, 1));
                else
                    Services.addUser(users, new PremiumUser(name, email, 100));
            }

            if(number == 7) {
                Services.mostCopies(sections);
            }

            if(number == 8) {
                System.out.println("Care este titlul carti la care se adauga exemplare??");
                String title = in.next();
                System.out.println("Cate exemplare vreti sa adaugati?");
                int numberOfCopies = in.nextInt();
                Services.addCopies(sections, title, numberOfCopies);

            }

            if(number == 9) {
                System.out.println("Care este titlul carti la care se ia din exemplare??");
                String title = in.next();
                System.out.println("Cate exemplare vreti sa luati?");
                int numberOfCopies = in.nextInt();
                Services.removeCopies(sections, title, numberOfCopies);

            }

            if(number == 10) {
                System.out.println("Care este numele dumneavoastra??");
                String name = in.next();
                System.out.println("Care este titlul cartii pe care vreti sa o returnati?");
                String title = in.next();
                Services.returnBook(sections, users, name, title);
            }







        }
    }
}