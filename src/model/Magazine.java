package model;

public class Magazine extends Book{
    private int issueNumber;

    public Magazine(int id, String title, String author, int publicationYear, String publisher, int issueNumber) {
        super(id, title, author, publicationYear, publisher);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "issueNumber=" + issueNumber +
                '}';
    }
}
