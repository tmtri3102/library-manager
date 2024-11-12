package model;

public class Novel extends Book {
    private String genre;

    public Novel(int id, String title, String author, int publicationYear, String publisher, String genre) {
        super(id, title, author, publicationYear, publisher);
        this.genre = genre;
    }
}
