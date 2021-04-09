package biblioteca.autors;

import biblioteca.books.Book;

import java.util.ArrayList;
import java.util.List;

public class Autor extends Human {
    private String section;

    public Autor(String name, String information, String section) {
        super(name, information);
        this.section = section;
    }

    public String getsection() {
        return section;
    }

    public void setsection(String section) {
        this.section = section;
    }


}
