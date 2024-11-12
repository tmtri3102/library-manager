package controller.sortBook;

import model.Book;
import storage.LibraryStorage;

import java.util.List;
import java.util.Collections;

public class BookSorter {
    static LibraryStorage libraryStorage = LibraryStorage.getInstance();

    public static void sortBook(List<Book> books) {
        books.sort((book1, book2) ->
                book1.getTitle().compareToIgnoreCase(book2.getTitle()));

        System.out.println("Books sorted by title:");
        for (Book book : books) {
            System.out.println(formatBookDetails(book));
        }

    }

    private static String formatBookDetails(Book book){
        return String.format("%s, %s",
                book.getTitle(),
                book.getAuthor());
    }
}