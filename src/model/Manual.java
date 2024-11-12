package model;

public class Manual extends Book {
    private String topic;
    private String version;

    public Manual(int id, String title, String author, int publicationYear, String publisher, String topic, String version) {
        super(id, title, author, publicationYear, publisher);
        this.topic = topic;
        this.version = version;
    }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    @Override
    public String getType() {
        return "Manual";
    }
}
