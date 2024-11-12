package controller.addBook;

import controller.displayBook.BookDisplayer;
import model.*;
import storage.LibraryStorage;

import java.util.List;
import java.util.Scanner;

public class BookAdder {
    static LibraryStorage libraryStorage = LibraryStorage.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void addBook(List<Book> books) {
        while (true) {
            System.out.println("Choose book type to add:");
            System.out.println("1. Comic Book");
            System.out.println("2. Novel");
            System.out.println("3. Textbook");
            System.out.println("4. Manual");
            System.out.println("5. Magazine");
            System.out.println("6. Back");
            int option = pickOption();
            if (option == 6) return;
            Book book = createBook(option);
            if (book != null) {
                books.add(book);
                System.out.println("Added the book \"" + book.getTitle() + "\" to the list");
                libraryStorage.writeBooks(books);
                BookDisplayer.displayBooks(books);
            }
        }
    }

    private static int pickOption() {
        int option = 0;
        try {
            System.out.print("What do you want to do?: ");
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Please enter a number.");
            scanner.nextLine();
        }
        return option;
    }

    private static Book createBook(int bookType) {
        int id = getValidatedInput("Enter Book ID: ", "^[1-9][0-9]{3}$", "Error: Please enter a 4-digit integer greater than 0.", true);
        String title = getValidatedInput("Enter Book Title: ", "^[A-Z][a-zA-Z\\s]*$", "Error: Please enter only letters and spaces. First letter must be capital.", false);
        String author = getValidatedInput("Enter Book Author: ", "^[A-Z][a-zA-Z\\s]*$", "Error: Please enter only letters and spaces. First letter must be capital.", false);
        int publishedYear = getValidatedInput("Enter Publication Year: ", "^(19|20)\\d{2}$", "Error: Please enter a valid year (1900-2099).", true);
        String bookTypeStr = getBookTypeString(bookType);

        switch (bookType) {
            case 1:
                String illustrator = getValidatedInput("Enter Illustrator: ", "^[A-Z][a-zA-Z\\s]*$", "Error: Please enter only letters and spaces. First letter must be capital.", false);
                String series = getValidatedInput("Enter Series: ", "^[A-Z][a-zA-Z\\s]*$", "Error: Please enter only letters and spaces. First letter must be capital.", false);
                return new ComicBook(id, title, author, publishedYear, bookTypeStr, illustrator, series);
            case 2:
                String genre = getValidatedInput("Enter Genre: ", "^[A-Z][a-zA-Z\\s]*$", "Error: Please enter only letters and spaces. First letter must be capital.", false);
                String language = getValidatedInput("Enter Language: ", "^[A-Z][a-zA-Z\\s]*$", "Error: Please enter only letters and spaces. First letter must be capital.", false);
                return new Novel(id, title, author, publishedYear, bookTypeStr, genre, language);
            case 3:
                String subject = getValidatedInput("Enter Subject: ", "^[A-Z][a-zA-Z\\s]*$", "Error: Please enter only letters and spaces. First letter must be capital.", false);
                String gradeLevel = getValidatedInput("Enter Grade Level: ", "^[A-Z][a-zA-Z\\s]*$", "Error: Please enter only letters and spaces. First letter must be capital.", false);
                return new Textbook(id, title, author, publishedYear, bookTypeStr, subject, gradeLevel);
            case 4:
                String topic = getValidatedInput("Enter Topic: ", "^[A-Z][a-zA-Z\\s]*$", "Error: Please enter only letters and spaces. First letter must be capital.", false);
                String version = getValidatedInput("Enter Version: ", "^[A-Z][a-zA-Z\\s]*$", "Error: Please enter only letters and spaces. First letter must be capital.", false);
                return new Manual(id, title, author, publishedYear, bookTypeStr, topic, version);
            case 5:
                int issueNumber = getValidatedInput("Enter Issue Number: ", "^[0-9]+$", "Error: Please enter a valid issue number.", true);
                String publicationFrequency = getValidatedInput("Enter Publication Frequency: ", "^[A-Z][a-zA-Z\\s]*$", "Error: Please enter only letters and spaces. First letter must be capital.", false);
                return new Magazine(id, title, author, publishedYear, bookTypeStr, issueNumber, publicationFrequency);
            default:
                System.out.println("Invalid book type.");
                return null;
        }
    }

    private static String getBookTypeString(int bookType) {
        switch (bookType) {
            case 1: return "Comic Book";
            case 2: return "Novel";
            case 3: return "Textbook";
            case 4: return "Manual";
            case 5: return "Magazine";
            default: return "Unknown";
        }
    }

    private static <T> T getValidatedInput(String prompt, String regex, String errorMessage, boolean isInteger) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.matches(regex)) {
                return isInteger ? (T) Integer.valueOf(input) : (T) input;
            }
            System.out.println(errorMessage);
        }
    }
}