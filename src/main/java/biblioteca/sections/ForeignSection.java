package biblioteca.sections;

import biblioteca.books.Book;

import java.util.Map;

public class ForeignSection extends Section {
    private String language;

    public ForeignSection(String name, Map<Book, Integer> books, String language) {
        super(name, books);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
