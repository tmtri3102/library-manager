package model;

public class ComicBook extends Book {
    private String illustrator;
    private String series;

    public ComicBook(int id, String title, String author, int publicationYear, String publisher, String illustrator, String series) {
        super(id, title, author, publicationYear, publisher);
        this.illustrator = illustrator;
        this.series = series;
    }

    public String getIllustrator() { return illustrator; }
    public void setIllustrator(String illustrator) { this.illustrator = illustrator; }

    public String getSeries() { return series; }
    public void setSeries(String series) { this.series = series; }

    @Override
    public String getType() {
        return "ComicBook";
    }
}
