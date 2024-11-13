package controller.sortBook;

import model.Book;
import storage.LibraryStorage;

import java.util.List;
import java.util.Scanner;

public class BookSorter {
    static LibraryStorage libraryStorage = LibraryStorage.getInstance();

    public static void main(String[] args) {
        List<Book> books = libraryStorage.readBooks(); // Assuming this method retrieves the list of books
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Sort by Title");
            System.out.println("2. Sort by ID");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    sortBookByTitle(books);
                    break;
                case 2:
                    sortBookById(books);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    // Bubble sort
    public static void sortBookByTitle(List<Book> books) {
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements by title
                if (books.get(j).getTitle().compareTo(books.get(j + 1).getTitle()) > 0) {
                    // Swap if elements are in the wrong order
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }

        System.out.println("Books sorted by title:");
        for (Book book : books) {
            System.out.println(formatBookDetails(book));
        }
    }
    // Insertion sort
    public static void sortBookById(List<Book> books) {
        int n = books.size();
        for (int i = 1; i < n; i++) {
            Book key = books.get(i);
            int j = i - 1;

            // Move elements of books[0..i-1] that are greater than key
            while (j >= 0 && books.get(j).getId() > key.getId()) {
                books.set(j + 1, books.get(j));
                j--;
            }
            books.set(j + 1, key);
        }

        System.out.println("Books sorted by ID:");
        for (Book book : books) {
            System.out.println(formatBookDetails(book));
        }
    }

    private static String formatBookDetails(Book book) {
        return String.format("ID: %d, Title: %s", book.getId(), book.getTitle());
    }
}