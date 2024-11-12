package model;

public class Newspaper extends Book{
    private String date;
    private String editor;

    public Newspaper(int id, String title, String author, int publicationYear, String publisher, String date) {
        super(id, title, author, publicationYear, publisher);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "date='" + date + '\'' +
                ", editor='" + editor + '\'' +
                '}';
    }
}
