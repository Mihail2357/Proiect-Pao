package biblioteca.books;

import biblioteca.autors.Autor;
import biblioteca.autors.Narator;

public class AudioBook extends Book {
    private int numberOfMinutes;
    private Narator narator;

    public AudioBook(String title, Autor autor, String section, String releaseDate, int numberOfMinutes, Narator narator) {
        super(title, autor, section, releaseDate);
        this.numberOfMinutes = numberOfMinutes;
        this.narator = narator;
    }

    public int getNumberOfMinutes() {
        return numberOfMinutes;
    }

    public void setNumberOfMinutes(int numberOfMinutes) {
        this.numberOfMinutes = numberOfMinutes;
    }

    public Narator getNarator() {
        return narator;
    }

    public void setNarator(Narator narator) {
        this.narator = narator;
    }

    public String toString()
    {
        return getTitle() + " " + getAutor().getName() + " " + getSection() + " " + getReleaseDate()+ " " + getNumberOfMinutes() + " " +getNarator().getName();
    }
}
