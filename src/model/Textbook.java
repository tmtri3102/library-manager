package model;



public class Textbook extends Book {
    private String subject;
    private String gradeLevel;

    public Textbook(int id, String title, String author, int publicationYear, String bookType, String subject, String gradeLevel) {
        super(id, title, author, publicationYear, bookType);
        this.subject = subject;
        this.gradeLevel = gradeLevel;
    }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(String gradeLevel) { this.gradeLevel = gradeLevel; }

    @Override
    public String getType() {
        return "Textbook";
    }
}
