package model;

public abstract class Book {
    private int id;
    private String title;
    private String author;
    private String bookType;
    private int publicationYear;

    public Book(int id, String title, String author, int publicationYear, String bookType) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.bookType = bookType;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getBookType() {
        return bookType;
    }
    public void setBookType(String bookType) {
        this.bookType = bookType;
    }



    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", bookType='" + bookType + '\'' +
                '}';
    }

    public abstract String getType();
}