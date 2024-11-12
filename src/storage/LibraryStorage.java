package storage;

import model.Book;

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
                // If file not exist, write header first:
                bw.write("ID,Title,Author,PublishedYear,Publisher");
                bw.newLine();
            }

            // Write book data as CSV line
//            String line = String.format("%d,%s,%s,%d,%s",
//                    book.getId(),
//                    book.getTitle(),
//                    book.getAuthor(),
//                    book.getPublicationYear(),
//                    book.getPublisher());
//
//            bw.write(line);
            for (Book book : books) {
                String line = String.format("%d,%s,%s,%d,%s",
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublicationYear(),
                    book.getPublisher());
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
                if (data.length == 5) {  // Ensure we have all fields
                    Book book = new Book(
                            Integer.parseInt(data[0]),   // id
                            data[1],                    // title
                            data[2],                    // author
                            Integer.parseInt(data[3]),  //  publishedYear
                            data[4]                    // publisher
                    );
                    books.add(book); // add to new list to print
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        return books;
    }
}
