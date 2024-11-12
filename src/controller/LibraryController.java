package controller;

import model.Book;
import storage.*;

import java.util.*;
import controller.displayBook.BookDisplayer;
import controller.addBook.BookAdder;


public class LibraryController {

    static LibraryStorage libraryStorage = LibraryStorage.getInstance();
    //    public static List<Book> books = libraryStorage.readBooks();

    private static List<Book> books;
    public LibraryController() {
        books = libraryStorage.readBooks();
    }

    /*==============  DISPLAY  ==============*/
    public void displayAllBooks() {
        BookDisplayer.displayBooks(books);
    }
//    public void displayAllBooks() {
//        try {
//            if (books.isEmpty()) {
//                System.out.println("No books found");
//            }
//            else {
//                System.out.println("All books:");
//                System.out.printf("%-5s %-30s %-20s %-15s %-20s%n", "ID", "Title", "Author", "Year", "Publisher");
//                System.out.println("----------------------------------------------------------------------------------");
//                for (Book book : books) {
//                    System.out.printf("%-5s %-30s %-20s %-15d %-20s%n",
//                            book.getId(), book.getTitle(), book.getAuthor(),
//                            book.getPublicationYear(), book.getPublisher());
//                }
//            }
//        } catch (NullPointerException e) {
//            System.out.println("No books found");
//        }
//    }

    /*==============  ADD  ==============*/
    public void addBook() {
        BookAdder.addBook(books);
    }

    public void addSampleBook() {
        Book sample1 = new Book(123, "To Kill a Mockingbird", "Harper Lee", 1969, "Lippincott");
        books.add(sample1);

        Book sample2 = new Book(456, "Educated", "Tara Westover", 1948, "Penguin");
        books.add(sample2);

        if(libraryStorage.readBooks().isEmpty()) {
            libraryStorage.writeBooks(sample1);
            libraryStorage.writeBooks(sample2);
            System.out.println("Added these books to the list");
            displayAllBooks();
        }
    }

//    public void addBook() {
//        Scanner scanner = new Scanner(System.in);
//
//        int id = 0;
//        String title = "";
//        String author = "";
//        String publisher = "";
//        int publishedYear = 0;
//
//        // Get book title
//        System.out.print("Enter book title: ");
//        title = scanner.nextLine().trim();
//        while (title.isEmpty()) {
//            System.out.println("Error: Title cannot be empty.");
//            System.out.print("Enter book title: ");
//            title = scanner.nextLine().trim();
//        }
//
//        // Get book author
//        System.out.print("Enter book author: ");
//        author = scanner.nextLine().trim();
//        while (!author.matches("^[a-zA-Z\\s]+$")) {
//            System.out.println("Error: Author should contain only letters and spaces.");
//            System.out.print("Enter book author: ");
//            author = scanner.nextLine().trim();
//        }
//        // Capitalize first letter of each word
//        String[] words = author.split("\\s+");
//        StringBuilder capitalizedAuthor = new StringBuilder();
//        for (String word : words) {
//            if (!word.isEmpty()) {
//                capitalizedAuthor.append(Character.toUpperCase(word.charAt(0)))
//                        .append(word.substring(1).toLowerCase())
//                        .append(" ");
//            }
//        }
//        author = capitalizedAuthor.toString().trim();
//
//        // Get publisher
//        System.out.print("Enter publisher: ");
//        publisher = scanner.nextLine().trim();
//        while (publisher.isEmpty()) {
//            System.out.println("Error: Publisher cannot be empty.");
//            System.out.print("Enter publisher: ");
//            publisher = scanner.nextLine().trim();
//        }
//
//        // Get published year
//        boolean validInput = false;
//        int currentYear = java.time.Year.now().getValue();
//        while (!validInput) {
//            try {
//                System.out.print("Enter published year: ");
//                publishedYear = Integer.parseInt(scanner.nextLine().trim());
//                if (publishedYear >= 1500 && publishedYear <= currentYear) {
//                    validInput = true;
//                } else {
//                    System.out.println("Error: Year must be between 1500 and " + currentYear + ".");
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Error: Please enter a valid 4-digit year.");
//            }
//        }
//
//        // Write to a CSV
//        Book newBook = new Book(id, title, author, publishedYear, publisher);
//        books.add(newBook);
//        libraryStorage.writeBooks(newBook);
//        System.out.println("Added the book \"" + newBook.getTitle() + "\" to the list");;
//    }

/*==============  REMOVE  ==============*/
public void removeBook() {
    System.out.println("Current books:");
    displayAllBooks();

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter book ID you want to remove: ");
    String idToRemove = scanner.nextLine().trim();
    Book bookToRemove = searchBookById(idToRemove);

    if (bookToRemove != null) {
        System.out.println("Removing this book: " + bookToRemove.getTitle());

        books.remove(bookToRemove);
        libraryStorage.writeBooks(bookToRemove);
        System.out.println("Book removed successfully. Current books:");
        displayAllBooks();
    } else {
        System.out.println("Book not found.");
    }
}

    private Book searchBookById(String id) {
        for (Book book : books) {
            if (book.getId() == Integer.parseInt(id)) {
                return book;
            }
        }
        return null; // Book not found
    }

/*==============  EDIT  ==============*/
    public void editBook() {
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
            libraryStorage.writeBooks(bookToEdit); // Assuming this method updates the book in the storage
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Book not found
    }

/*==============  SEARCH  ==============*/
    public void searchBook() {
        Scanner scanner = new Scanner(System.in);
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
