package model;

public class Newspaper extends Book{
    private String date;
    private String editor;

    public Newspaper(String title, String author, String ISBN, String publisher, int publicationYear, int pages, boolean isAvailable, String date, String editor) {
        super(title, author, ISBN, publisher, publicationYear, pages, isAvailable);
        this.date = date;
        this.editor = editor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "date='" + date + '\'' +
                ", editor='" + editor + '\'' +
                '}';
    }
}
