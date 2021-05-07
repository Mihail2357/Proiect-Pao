package biblioteca.books;

import biblioteca.autors.Autor;

public abstract class Book {
    private String title;
    private Autor autor;
    private String section;
    private String releaseDate;

    public Book(String title, Autor autor, String section, String releaseDate) {
        this.title = title;
        this.autor = autor;
        this.section = section;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
