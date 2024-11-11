package model;
// implements Clonable? public abstract Product clone();

public class Book {
    private String title;
    private String author;
    private String publisher;
    private int publishedYear;
    private boolean isAvailable;

//    public Book(){}
    public Book(String title, String author, String publisher,int publishedYear,  boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.isAvailable = isAvailable;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", isAvailable=" + isAvailable +
                ", publishedYear=" + publishedYear +
                ", publisher='" + publisher + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
