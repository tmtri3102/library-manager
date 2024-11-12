package controller.searchBook;
import model.Book;
import storage.LibraryStorage;

import java.util.List;
import java.util.Scanner;
public class BookSearcher {
    static LibraryStorage libraryStorage = LibraryStorage.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void searchBook(List<Book> books) {
        System.out.print("Enter a search term (title/author/publisher/year): ");
        String searchTerm = scanner.nextLine().trim().toLowerCase();

        boolean foundAny = false;
        System.out.println("Matching books:");

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm) ||
                    book.getAuthor().toLowerCase().contains(searchTerm) ||
                    book.getPublisher().toLowerCase().contains(searchTerm) ||
                    String.valueOf(book.getPublicationYear()).contains(searchTerm)) {
                System.out.println(book);
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("No books found matching the search term: " + searchTerm);
        }
    }
}
