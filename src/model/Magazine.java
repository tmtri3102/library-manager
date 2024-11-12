package model;

public class Magazine extends Book {
    private int issueNumber;
    private String publicationFrequency;

    public Magazine(int id, String title, String author, int publicationYear, String publisher, int issueNumber, String publicationFrequency) {
        super(id, title, author, publicationYear, publisher);
        this.issueNumber = issueNumber;
        this.publicationFrequency = publicationFrequency;
    }

    public int getIssueNumber() { return issueNumber; }
    public void setIssueNumber(int issueNumber) { this.issueNumber = issueNumber; }

    public String getPublicationFrequency() { return publicationFrequency; }
    public void setPublicationFrequency(String publicationFrequency) { this.publicationFrequency = publicationFrequency; }

    @Override
    public String getType() {
        return "Magazine";
    }
}
