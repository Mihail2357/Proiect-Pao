package biblioteca.autors;

import biblioteca.books.AudioBook;

import java.util.ArrayList;
import java.util.List;

public class Narator extends Human{
    private String language;

    public Narator(String name, String information, String language) {
        super(name, information);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
