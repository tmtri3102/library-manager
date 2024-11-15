package controller.searchBook;
import model.Book;
import storage.LibraryStorage;

import java.util.List;
import java.util.Scanner;

public class BookSearcher {
    static LibraryStorage libraryStorage = LibraryStorage.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void searchBook(List<Book> books) {
        System.out.print("Enter a book title: ");
        String searchTerm = scanner.nextLine().trim();

        // Regex: Starts with capital letter, followed by letters and spaces
        if (!searchTerm.matches("^[A-Z][a-zA-Z ]*$")) {
            System.out.println("Invalid title format. First letter must be capital, only letters and spaces allowed.");
            return;
        }

        boolean foundAny = false;
        System.out.println("Matching books:");

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println(book);
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("No books found matching the title: " + searchTerm);
        }
    }
}