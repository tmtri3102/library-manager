package controller;

import model.Book;
import storage.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



public class LibraryController {

    static LibraryStorage libraryStorage = LibraryStorage.getInstance();
    public static List<Book> books = libraryStorage.readBooks();
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

        Book sample1 = new Book("To Kill a Mockingbird", "Harper Lee", "123456", "Lippincott & Co.", 1960, 169, true);
        books.add(sample1);
        libraryStorage.writeBooks(sample1);

        Book sample2 = new Book("Educated", "Tara Westover", "456789", "Penguin", 1960, 259, true);
        books.add(sample2);
        libraryStorage.writeBooks(sample2);
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

        // Write to a CSV
        Book newBook = new Book(title, author, isbn, publisher, publishedYear, pages, isAvailable);
        books.add(newBook);
        libraryStorage.writeBooks(newBook);
        System.out.println("Added the book \"" + newBook.getTitle() + "\" to the list");;
    }

/*==============  REMOVE  ==============*/
    public void removeBook(Book book) {
        books.remove(book);
    }

    /*==============  SEARCH  ==============*/
    public void editBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the book you want to edit: ");
        String titleToEdit = scanner.nextLine().trim();

        // Search for the book using the title
        Book bookToEdit = searchBookByTitle(titleToEdit);

        if (bookToEdit != null) {
            System.out.println("Editing book: " + bookToEdit);

            // Prompt for new details
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

            System.out.print("Enter new number of pages (leave blank to keep current): ");
            String pagesInput = scanner.nextLine().trim();
            if (!pagesInput.isEmpty()) {
                try {
                    int newPages = Integer.parseInt(pagesInput);
                    bookToEdit.setPages(newPages);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number of pages. Keeping current pages.");
                }
            }

            System.out.print("Is the book available (true/false)? (leave blank to keep current): ");
            String availabilityInput = scanner.nextLine().trim().toLowerCase();
            if (!availabilityInput.isEmpty()) {
                if (availabilityInput.equals("true") || availabilityInput.equals("false")) {
                    bookToEdit.setAvailable(Boolean.parseBoolean(availabilityInput));
                } else {
                    System.out.println("Invalid input. Keeping current availability status.");
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

    public void searchBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a search term (title, author, publisher, or year): ");
        String searchTerm = scanner.nextLine().trim().toLowerCase();

        List<Book> matchingBooks = new ArrayList<>();

        for (Book book : books) {
            // Check if the search term is in the title, author, publisher, or published year
            if (book.getTitle().toLowerCase().contains(searchTerm) ||
                    book.getAuthor().toLowerCase().contains(searchTerm) ||
                    book.getPublisher().toLowerCase().contains(searchTerm) ||
                    String.valueOf(book.getPublicationYear()).contains(searchTerm)) {
                matchingBooks.add(book);
            }
        }

        // Display the results
        if (matchingBooks.isEmpty()) {
            System.out.println("No books found matching the search term: " + searchTerm);
        } else {
            System.out.println("Matching books:");
            for (Book book : matchingBooks) {
                System.out.println(book);
            }
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
