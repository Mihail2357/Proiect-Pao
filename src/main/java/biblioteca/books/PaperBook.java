package biblioteca.books;

import biblioteca.autors.Autor;

public class PaperBook extends Book{
    private String publisher;
    private int numberOfPages;

    public PaperBook(String title, Autor autor, String section, String releaseDate, String publisher, int numberOfPages) {
        super(title, autor, section, releaseDate);
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String toString()
    {
        return getTitle() + " " + getAutor().getName() + " " + getSection() + " " + getPublisher()+ " " + getNumberOfPages();
    }
}
