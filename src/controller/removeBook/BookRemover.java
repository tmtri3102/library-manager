package controller.removeBook;

import controller.displayBook.BookDisplayer;
import model.Book;
import storage.LibraryStorage;

import java.util.List;
import java.util.Scanner;

public class BookRemover {
    static LibraryStorage libraryStorage = LibraryStorage.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void removeBook(List<Book> books) {
        while (true) {
            System.out.println("Current books:");
            BookDisplayer.displayBooks(books);

            System.out.print("Enter book ID you want to remove: ");
            String idToRemove = scanner.nextLine().trim();

            // Go back to main menu
            if (idToRemove.equalsIgnoreCase("q")) {
                System.out.println("================================");
                System.out.println("Returning to main menu.");
                System.out.println("================================");
                return;
            }

            try {
                Book bookToRemove = searchBookById(books, idToRemove);

                if (bookToRemove != null) {
                    System.out.println("Removing this book: " + bookToRemove.getTitle());
                    books.remove(bookToRemove);
                    libraryStorage.writeBooks(books); // update

                    System.out.println("Book removed successfully. Current books:");
                    BookDisplayer.displayBooks(books);
                    return;
                } else {
                    System.out.println("Book not found. Try again or 'q' to quit.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again or 'q' to quit.");
            }
        }
    }

    private static Book searchBookById(List<Book> books, String id) {
        for (Book book : books) {
            if (book.getId() == Integer.parseInt(id)) {
                return book; // Return the found book
            }
        }
        return null; // Book not found
    }
}