package model;

public class Novel extends Book {
    private String genre;
    private String language;

    public Novel(int id, String title, String author, int publicationYear, String publisher, String genre, String language) {
        super(id, title, author, publicationYear, publisher);
        this.genre = genre;
        this.language = language;
    }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    @Override
    public String getType() {
        return "Novel";
    }
}
