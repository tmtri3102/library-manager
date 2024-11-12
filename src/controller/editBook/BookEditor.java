package controller.editBook;
import model.Book;
import storage.LibraryStorage;

import java.util.List;
import java.util.Scanner;

import static controller.LibraryController.books;

public class BookEditor {
    static LibraryStorage libraryStorage = LibraryStorage.getInstance();
    public static void editBook(List<Book> books) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the book you want to edit: ");
        String titleToEdit = scanner.nextLine().trim();

        // Search for the book using the title
        Book bookToEdit = searchBookByTitle(titleToEdit);

        if (bookToEdit != null) {
            System.out.println("Editing book: " + bookToEdit);

            System.out.print("Enter id (leave blank to keep current): ");
            String idInput = scanner.nextLine().trim();
            if (!idInput.isEmpty()) {
                try {
                    int newPublishedYear = Integer.parseInt(idInput);
                    bookToEdit.setPublicationYear(newPublishedYear);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid year input. Keeping current year.");
                }
            }
            System.out.print("Enter new author (leave blank to keep current): ");
            String newAuthor = scanner.nextLine().trim();
            if (!newAuthor.isEmpty()) {
                bookToEdit.setAuthor(newAuthor);
            }

            System.out.print("Enter new publisher (leave blank to keep current): ");
            String newPublisher = scanner.nextLine().trim();
            if (!newPublisher.isEmpty()) {
                bookToEdit.setPublisher(newPublisher);
            }

            System.out.print("Enter new published year (leave blank to keep current): ");
            String yearInput = scanner.nextLine().trim();
            if (!yearInput.isEmpty()) {
                try {
                    int newPublishedYear = Integer.parseInt(yearInput);
                    bookToEdit.setPublicationYear(newPublishedYear);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid year input. Keeping current year.");
                }
            }

            // Update the storage
            libraryStorage.writeBooks(books);
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Book not found
    }
}
