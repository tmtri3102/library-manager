package storage;
import model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryStorage {
    private LibraryStorage(){}
    private static LibraryStorage instance;

    public static LibraryStorage getInstance(){
        if(instance == null){
            instance = new LibraryStorage();
        }
        return instance;
    }

//    public void writeBooks(List<Book> books){
//        File csvFile = new File("books.csv");
//        try {
//            if(!csvFile.exists()){
//                csvFile.createNewFile();
//            }
//        }
//        catch (IOException e){
//            throw new RuntimeException("Error creating new file", e);
//        }
//        try{
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(csvFile));
//            objectOutputStream.writeObject(books);
//        } catch (IOException e){
//            throw new RuntimeException("Error writing books to CSV file", e);
//        }
//    }
    public void writeBooks(Book book) {
        String csvFile = "books.csv";
        boolean fileExists = new File(csvFile).exists();
        try (FileWriter fw = new FileWriter(csvFile, true);  // true => add, FileWriter: open file & write
             BufferedWriter bw = new BufferedWriter(fw)) {

            if (!fileExists) {
                // If file doesn't exist, write header first:
                bw.write("Title,Author,ISBN,Publisher,PublishedYear,Pages,IsAvailable");
                bw.newLine();
            }

            // Write book data as CSV line
            String line = String.format("%s,%s,%s,%s,%d,%d,%b",
                    book.getTitle(),
                    book.getAuthor(),
                    book.getISBN(),
                    book.getPublisher(),
                    book.getPublicationYear(),
                    book.getPages(),
                    book.isAvailable());

            bw.write(line);
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }

//    public List<Book> readBooks() {
//        File csvFile = new File("data/books.txt");
//        try {
//            ObjectInputStream os = new ObjectInputStream(new FileInputStream(csvFile));
//            Object o = os.readObject();
//            return (List<Book>) o;
//        } catch (EOFException e) {
//            System.out.println("File is empty");
//            return new ArrayList<>();
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException("Error reading drinks from file", e);
//        }
//    }
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
                    Book book = new Book(
                            data[0],                    // title
                            data[1],                    // author
                            data[2],                    // isbn
                            data[3],                    // publisher
                            Integer.parseInt(data[4]),  // publishedYear
                            Integer.parseInt(data[5]),  // pages
                            Boolean.parseBoolean(data[6]) // isAvailable
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
