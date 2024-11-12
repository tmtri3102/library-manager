package controller.addBook;
import controller.displayBook.BookDisplayer;
import model.Book;
import model.TextBook;
import storage.LibraryStorage;

import java.util.List;
import java.util.Scanner;


public class BookAdder {
    static LibraryStorage libraryStorage = LibraryStorage.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    public static void addBook(List<Book> books) {
        Scanner scanner = new Scanner(System.in);

        int id = promptForId();
        String title = promptForTitle();
        String author = promptForAuthor();
        String publisher = promptForPublisher();
        int publishedYear = promptForPublicationYear();

// Get book title
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
        // Write to a CSV

        Book newBook = new TextBook(id, title, author, publishedYear, publisher);
        books.add(newBook);
        System.out.println("Added the book \"" + newBook.getTitle() + "\" to the list");
        libraryStorage.writeBooks(books);
        BookDisplayer.displayBooks(books);
    }

    private static int promptForId() {
        String idInput;
        boolean validInput = false;
        int id = -1;

        while (!validInput) {
            System.out.print("Enter Book ID: ");
            idInput = scanner.nextLine().trim();
            if (idInput.matches("^[1-9][0-9]{3}$")) {
                id = Integer.parseInt(idInput);
                validInput = true;
            } else {
                System.out.println("Error: Please enter 4-digit integer bigger than 0");
            }
        }
        return id;
    }
    private static String promptForTitle() {
        String title = "";
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter Book Title: ");
            title = scanner.nextLine().trim();

            if (title.matches("^[A-Z][a-zA-Z\\s]*$")) {
                validInput = true;
            } else {
                System.out.println("Error: Please enter only letters and spaces. First letter must be capital.");
            }
        }
        return title;
    }
    private static String promptForAuthor() {
        String author = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter Book Author: ");
            author = scanner.nextLine().trim();

            if (author.matches("^[A-Z][a-zA-Z\\s]*$")) {
                validInput = true;
            } else {
                System.out.println("Error: Please enter only letters and spaces. First letter must be capital.");
            }
        }
        return author;
    }
    private static int promptForPublicationYear() {
        String yearInput="";
        boolean validInput = false;
        int year = -1;

        while (!validInput) {
            System.out.print("Enter Publication Year: ");
            yearInput = scanner.nextLine().trim();

            if (yearInput.matches("^(19|20)\\d{2}$")) {
                year = Integer.parseInt(yearInput);
                validInput = true;
            } else {
                System.out.println("Error: Please enter 4 numbers (1900-2099).");
            }
        }
        return year;
    }
    private static String promptForPublisher() {
        String publisher = "";
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter Book Publisher: ");
            publisher = scanner.nextLine().trim();

            if (publisher.matches("^[A-Z][a-zA-Z0-9\\s]*$")) { // Validation for publisher name
                validInput = true;
            } else {
                System.out.println("Error: Please enter only letters and spaces. First letter must be capital.");
            }
        }
        return publisher;
    }

//    public void addSampleBook() {
//        Book sample1 = new Book(123, "To Kill a Mockingbird", "Harper Lee", 1969, "Lippincott");
//        books.add(sample1);
//
//        Book sample2 = new Book(456, "Educated", "Tara Westover", 1948, "Penguin");
//        books.add(sample2);
//
//        if(libraryStorage.readBooks().isEmpty()) {
//            libraryStorage.writeBooks(sample1);
//            libraryStorage.writeBooks(sample2);
//            System.out.println("Added these books to the list");
//            displayAllBooks();
//        }
//    }
}
