package storage;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryStorage {
    private LibraryStorage() {
    }

    private static LibraryStorage instance;

    public static LibraryStorage getInstance() {
        if (instance == null) {
            instance = new LibraryStorage();
        }
        return instance;
    }

    public void writeBooks(List<Book> books) {
        String csvFile = "books.csv";
        boolean fileExists = new File(csvFile).exists();
        try (FileWriter fw = new FileWriter(csvFile);
             BufferedWriter bw = new BufferedWriter(fw)) {

            if (!fileExists) {
                // Extended header to include specific book type details
                bw.write("ID,Title,Author,PublishedYear,BookType,SpecificDetail1,SpecificDetail2");
                bw.newLine();
            }

            for (Book book : books) {
                String specificDetail1 = "";
                String specificDetail2 = "";

                // Add specific details based on book type
                if (book instanceof ComicBook) {
                    specificDetail1 = ((ComicBook) book).getIllustrator();
                    specificDetail2 = ((ComicBook) book).getSeries();
                } else if (book instanceof Novel) {
                    specificDetail1 = ((Novel) book).getGenre();
                    specificDetail2 = ((Novel) book).getLanguage();
                } else if (book instanceof Textbook) {
                    specificDetail1 = ((Textbook) book).getSubject();
                    specificDetail2 = ((Textbook) book).getGradeLevel();
                } else if (book instanceof Manual) {
                    specificDetail1 = ((Manual) book).getTopic();
                    specificDetail2 = ((Manual) book).getVersion();
                } else if (book instanceof Magazine) {
                    specificDetail1 = String.valueOf(((Magazine) book).getIssueNumber());
                    specificDetail2 = ((Magazine) book).getPublicationFrequency();
                }

                String line = String.format("%d,%s,%s,%d,%s,%s,%s",
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublicationYear(),
                        book.getBookType(),
                        specificDetail1,
                        specificDetail2);
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public List<Book> readBooks() {
        List<Book> books = new ArrayList<>();
        String csvFile = "books.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip the header line
            br.readLine();
            String line;

            // Read data lines
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {  // Ensure we have all fields
                    int id = Integer.parseInt(data[0]);
                    String title = data[1];
                    String author = data[2];
                    int publishedYear = Integer.parseInt(data[3]);
                    String bookType = data[4];
                    String specificDetail1 = data[5];
                    String specificDetail2 = data[6];

                    Book book = createBookBasedOnType(
                            id, title, author, publishedYear, bookType,
                            specificDetail1, specificDetail2
                    );

                    if (book != null) {
                        books.add(book);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        return books;
    }

    private Book createBookBasedOnType(
            int id, String title, String author, int publishedYear,
            String bookType, String specificDetail1, String specificDetail2
    ) {
        switch (bookType) {
            case "Comic Book":
                return new ComicBook(id, title, author, publishedYear, bookType, specificDetail1, specificDetail2);
            case "Novel":
                return new Novel(id, title, author, publishedYear, bookType, specificDetail1, specificDetail2);
            case "Textbook":
                return new Textbook(id, title, author, publishedYear, bookType, specificDetail1, specificDetail2);
            case "Manual":
                return new Manual(id, title, author, publishedYear, bookType, specificDetail1, specificDetail2);
            case "Magazine":
                return new Magazine(id, title, author, publishedYear, bookType,
                        Integer.parseInt(specificDetail1), specificDetail2);
            default:
                System.out.println("Unknown book type: " + bookType);
                return null;
        }
    }
}