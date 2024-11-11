package controller;

import model.Book;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LibraryController {
    private List<Book> books  = new ArrayList<>();

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

    public void addSampleBook() {
        // Directly returning a sample book without user input
        String title = "To Kill a Mockingbird";
        String author = "Harper Lee";
        String isbn = "9780061120084";
        String publisher = "J.B. Lippincott & Co.";
        int publishedYear = 1960;
        int pages = 281;
        boolean isAvailable = true;

        Book sample1 = new Book(title, author, isbn, publisher, publishedYear, pages, isAvailable);
        books.add(sample1);
        Book sample2 = new Book(title, author, isbn, publisher, publishedYear, pages, isAvailable);
        books.add(sample2);
        System.out.println("Added sample books to the list");
    }

    /*==============  ADD  ==============*/
    public void addBook() {
        Scanner scanner = new Scanner(System.in);

        String title = "";
        String author = "";
        String isbn = "";
        String publisher = "";
        int publishedYear = 0;
        int pages = 0;
        boolean isAvailable = false;

        // Get book title
        System.out.print("Enter book title: ");
        title = scanner.nextLine().trim();
        while (title.isEmpty()) {
            System.out.println("Error: Title cannot be empty.");
            System.out.print("Enter book title: ");
            title = scanner.nextLine().trim();
        }

        // Get book author
        System.out.print("Enter book author: ");
        author = scanner.nextLine().trim();
        while (!author.matches("^[a-zA-Z\\s]+$")) {
            System.out.println("Error: Author should contain only letters and spaces.");
            System.out.print("Enter book author: ");
            author = scanner.nextLine().trim();
        }
        // Capitalize first letter of each word
        String[] words = author.split("\\s+");
        StringBuilder capitalizedAuthor = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalizedAuthor.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        author = capitalizedAuthor.toString().trim();

        // Get ISBN
        System.out.print("Enter ISBN: ");
        isbn = scanner.nextLine().trim();
        while (!isbn.matches("^\\d{4,9}$")) {
            System.out.println("Error: ISBN must be between 4 and 9 digits.");
            System.out.print("Enter ISBN: ");
            isbn = scanner.nextLine().trim();
        }

        // Get publisher
        System.out.print("Enter publisher: ");
        publisher = scanner.nextLine().trim();
        while (publisher.isEmpty()) {
            System.out.println("Error: Publisher cannot be empty.");
            System.out.print("Enter publisher: ");
            publisher = scanner.nextLine().trim();
        }

        // Get published year
        boolean validInput = false;
        int currentYear = java.time.Year.now().getValue();
        while (!validInput) {
            try {
                System.out.print("Enter published year: ");
                publishedYear = Integer.parseInt(scanner.nextLine().trim());
                if (publishedYear >= 1500 && publishedYear <= currentYear) {
                    validInput = true;
                } else {
                    System.out.println("Error: Year must be between 1500 and " + currentYear + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid 4-digit year.");
            }
        }

        // Get number of pages
        validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter number of pages: ");
                pages = Integer.parseInt(scanner.nextLine().trim());
                if (pages >= 100 && pages <= 1000) {
                    validInput = true;
                } else {
                    System.out.println("Error: Pages must be between 100 and 1000.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }

        // Get availability
        validInput = false;
        while (!validInput) {
            System.out.print("Is the book available (true/false)?: ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                isAvailable = Boolean.parseBoolean(input);
                validInput = true;
            } else {
                System.out.println("Error: Please enter either 'true' or 'false'.");
            }
        }

        // Book object
        Book newBook = new Book(title, author, isbn, publisher, publishedYear, pages, isAvailable);
        books.add(newBook);
        System.out.println("Added the book \"" + newBook.getTitle() + "\" to the list");;
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
