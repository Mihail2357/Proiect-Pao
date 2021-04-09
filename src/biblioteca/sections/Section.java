package biblioteca.sections;

import biblioteca.books.Book;

import java.util.HashMap;
import java.util.Map;

public class Section implements Comparable<Section> {
    private String name;
    private Map<Book, Integer> books = new HashMap<>();

    public Section(String name, Map<Book, Integer> books) {
        this.name = name;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Book, Integer> getBooks() {
        return books;
    }

    public void setBooks(Map<Book, Integer> books) {
        this.books = books;
    }

    @Override
    public int compareTo(Section o) {
        return this.name.compareTo(o.getName());
    }
}
