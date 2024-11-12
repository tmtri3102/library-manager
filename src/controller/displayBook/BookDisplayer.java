package controller.displayBook;

import model.Book;
import java.util.List;

public class BookDisplayer {
    public static void displayBooks(List<Book> books) {
        try {
            if (books.isEmpty()) {
                System.out.println("No books found");
            }
            else {
                System.out.println("All books:");
                System.out.printf("%-5s %-15s %-20s %-15s %-20s%n", "ID", "Title", "Author", "Year", "Publisher");
                System.out.println("----------------------------------------------------------------------------------");
                for (Book book : books) {
                    System.out.printf("%-5s %-15s %-20s %-15d %-20s%n",
                            book.getId(), book.getTitle(), book.getAuthor(),
                            book.getPublicationYear(), book.getBookType());
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No books found");
        }
    }
}
