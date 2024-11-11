package model;

public class Magazine extends Book{
    private int issueNumber;
    private String month;

    public Magazine(String title, String author, String ISBN, String publisher, int publicationYear, int pages, boolean isAvailable, int issueNumber, String month) {
        super(title, author, ISBN, publisher, publicationYear, pages, isAvailable);
        this.issueNumber = issueNumber;
        this.month = month;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "issueNumber=" + issueNumber +
                ", month='" + month + '\'' +
                '}';
    }
}
