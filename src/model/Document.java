package model;

public class Document extends Book {
    private String documentType;

    public Document(String title, String author, String ISBN, String publisher, int publicationYear, int pages, boolean isAvailable, String documentType) {
        super(title, author, ISBN, publisher, publicationYear, pages, isAvailable);
        this.documentType = documentType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentType='" + documentType + '\'' +
                '}';
    }
}
