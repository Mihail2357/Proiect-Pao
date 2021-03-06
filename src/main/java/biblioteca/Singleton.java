package biblioteca;

import biblioteca.autors.Autor;
import biblioteca.autors.Narator;
import biblioteca.books.AudioBook;
import biblioteca.books.Book;
import biblioteca.books.PaperBook;
import biblioteca.users.LimitedUser;
import biblioteca.users.PremiumUser;
import biblioteca.users.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Singleton {
    private static Singleton instance;

    private Singleton(){ }

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                //createInstance();
                createInstanceIfNull();
            }
        }
        return instance;
    }
    private static void createInstance(){
        instance = new Singleton();
    }
    private static void createInstanceIfNull(){
        if(instance == null){
            instance = new Singleton();
        }
    }

    public void createNewFile(String filePath) {
        try {
            Writer fileWriter = new FileWriter(filePath, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> readBooks(String file)
    {   List<Book> books = new ArrayList<>();

        try {
            FileReader filereader = new FileReader(file);

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(0)
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData){
                if(row[0].equals("AudioBook")) {
                    AudioBook audioBook = new AudioBook(row[1], new Autor(row[2], row[3], row[4]), row[5], row[6], Integer.parseInt(row[7]), new Narator(row[8], row[9], row[10]));
                    books.add(audioBook);
                }
                if(row[0].equals("PaperBook")) {
                    PaperBook paperBook = new PaperBook(row[1], new Autor(row[2], row[3], row[4]), row[5], row[6], row[7], Integer.parseInt(row[8]));
                    books.add(paperBook);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<User> readUsers(String file)
    {   List<User> users = new ArrayList<>();

        try {
            FileReader filereader = new FileReader(file);

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(0)
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData){
                if(row[0].equals("LimitedUser")) {
                    LimitedUser limitedUser = new LimitedUser(row[1], row[2], Integer.parseInt(row[3]));
                    users.add(limitedUser);
                }
                if(row[0].equals("PremiumUser")) {
                   PremiumUser premiumUser = new PremiumUser(row[1], row[2], Integer.parseInt(row[3]));
                    users.add(premiumUser);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void writeInCsv(String filePath, String[] data){
        File file = new File(filePath);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }

            PrintWriter outputfile = new PrintWriter(new FileWriter(file, true));
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(data);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
