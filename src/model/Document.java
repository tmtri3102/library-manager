package model;

public class Document extends Book {
    private String documentType;

    public Document(int id, String title, String author, int publicationYear, String publisher, String documentType) {
        super(id, title, author, publicationYear, publisher);
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
