package controller;


import model.Book;
import storage.*;

import java.util.*;
import controller.displayBook.BookDisplayer;
import controller.addBook.BookAdder;
import controller.removeBook.BookRemover;
import controller.editBook.BookEditor;
import controller.searchBook.BookSearcher;


public class LibraryController {

    static LibraryStorage libraryStorage = LibraryStorage.getInstance();
    public static List<Book> books = libraryStorage.readBooks();


    /*==============  DISPLAY  ==============*/
    public void displayAllBooks() {
        BookDisplayer.displayBooks(books);
    }

    /*==============  ADD  ==============*/
    public void addBook() {
        BookAdder.addBook(books);
    }

    /*==============  REMOVE  ==============*/
    public void removeBook() {
        BookRemover.removeBook(books);
    }

    /*==============  EDIT  ==============*/
    public void editBook() {
        BookEditor.editBook(books);
    }

    /*==============  SEARCH  ==============*/
    public void searchBook(){
        BookSearcher.searchBook(books);
    }
//    public void searchBook() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter a search term (title/author/publisher/year): ");
//        String searchTerm = scanner.nextLine().trim().toLowerCase();
//
//        boolean foundAny = false;
//        System.out.println("Matching books:");
//
//        for (Book book : books) {
//            if (book.getTitle().toLowerCase().contains(searchTerm) ||
//                    book.getAuthor().toLowerCase().contains(searchTerm) ||
//                    book.getPublisher().toLowerCase().contains(searchTerm) ||
//                    String.valueOf(book.getPublicationYear()).contains(searchTerm)) {
//                System.out.println(book);
//                foundAny = true;
//            }
//        }
//
//        if (!foundAny) {
//            System.out.println("No books found matching the search term: " + searchTerm);
//        }
//    }
//
//    public List<Book> editBook() {
//        if (books.isEmpty()) {
//            System.out.println("No book found.");
//        } else {
//            System.out.println("Editing book:");
//            String id = inputValidation.isInTheList();
//            for (Drink drink : books) {
//                if (drink.getId().equals(id)) {
//                    System.out.println(drink);
//                    drinkInformation.inputDrinkInformation(drink);
//                    break;
//                }
//            }
//            System.out.println("Drink id " + id + " edited");
//        }
//        return books;
//    }
//
//    public enum SearchType {
//        TITLE,
//        AUTHOR,
//        PUBLISHER
//    }
//    public Book binarySearch(String searchText, SearchType searchType) {
//        int start = 0;
//        int end = books.size() - 1;
//
//        while (start <= end) {
//            int mid = (start + end) / 2;
//            int comparisonResult = getComparisonResult(searchText, searchType, mid);
//            if (comparisonResult == 0) {
//                return books.get(mid);
//            } else if (comparisonResult < 0) {
//                end = mid - 1;
//            } else {
//                start = mid + 1;
//            }
//        }
//        return null;
//    }
//    private int getComparisonResult(String searchText, SearchType searchType, int mid) {
//        String midBookValue;
//        switch (searchType) {
//            case TITLE:
//                midBookValue = books.get(mid).getTitle();
//                break;
//            case AUTHOR:
//                midBookValue = books.get(mid).getAuthor();
//                break;
//            case PUBLISHER:
//                midBookValue = books.get(mid).getPublisher();
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid search type");
//        }
//        return midBookValue.compareToIgnoreCase(searchText);
//    }

/*==============  SORT  ==============*/
    public void sortBook() {
        Collections.sort(books, (book1, book2) ->
                book1.getTitle().compareToIgnoreCase(book2.getTitle())
        );

        System.out.println("Books sorted by title:");
        for (Book book : books) {
            System.out.println(formatBookDetails(book));
        }

    }

    private String formatBookDetails(Book book) {
        return String.format("%s, %s",
                book.getTitle(),
                book.getAuthor());
    }
}
