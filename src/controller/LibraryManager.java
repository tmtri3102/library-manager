package controller;

import model.Book;

import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    private List<Book> books;

    /*==============  DISPLAY  ==============*/
    public void displayAllBooks() {
        try {
            if (books.isEmpty()) {
                System.out.println("No books found");
            }
            else {
                System.out.println("All books:");
                for (Book book : books) {
                    System.out.println(book);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No books found");
        }
    }

    /*==============  ADD  ==============*/
    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter published year: ");
        int publishedYear = scanner.nextInt();
        System.out.print("Is the book available: ");
        boolean isAvailable = scanner.nextBoolean();
        Book book = new Book(title, author, publisher, publishedYear, isAvailable);
        return book;
    }

    /*==============  REMOVE  ==============*/
    public void removeBook(Book book) {
        books.remove(book);
    }

    /*==============  SEARCH  ==============*/
    public Book searchBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search title, author or publisher name: ");
        String searchResult = scanner.nextLine();
        SearchType[] searchTypes = {SearchType.TITLE, SearchType.AUTHOR, SearchType.PUBLISHER};

        // Run binary search for each search type
        for (SearchType searchType : searchTypes) {
            Book foundBook = binarySearch(searchResult, searchType);
            if (foundBook != null) {
                return foundBook; // Return the first found book
            }
        }
        return null; // when not found
    }
    public enum SearchType {
        TITLE,
        AUTHOR,
        PUBLISHER
    }
    public Book binarySearch(String searchText, SearchType searchType) {
        int start = 0;
        int end = books.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int comparisonResult = getComparisonResult(searchText, searchType, mid);
            if (comparisonResult == 0) {
                return books.get(mid);
            } else if (comparisonResult < 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return null;
    }
    private int getComparisonResult(String searchText, SearchType searchType, int mid) {
        String midBookValue;
        switch (searchType) {
            case TITLE:
                midBookValue = books.get(mid).getTitle();
                break;
            case AUTHOR:
                midBookValue = books.get(mid).getAuthor();
                break;
            case PUBLISHER:
                midBookValue = books.get(mid).getPublisher();
                break;
            default:
                throw new IllegalArgumentException("Invalid search type");
        }
        return midBookValue.compareToIgnoreCase(searchText);
    }


}
