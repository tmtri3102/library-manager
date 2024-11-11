package view;

import model.*;
import controller.LibraryManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static  LibraryManager libraryManager = new LibraryManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("=============================");
            System.out.println("Welcome to Library Manager");
            System.out.println("1. Show all books");
            System.out.println("2. Add book");
            System.out.println("3. Edit book");
            System.out.println("4. Remove book");
            System.out.println("5. Sort book list");
            System.out.println("6. Search book by type");
            System.out.println("7. Exit");

            switch (pickOption()) {
                case 1:
                    libraryManager.displayAllBooks();
                    break;
                case 2:
                    libraryManager.addBook();
                    break;
                case 3:
//                    libraryManager.editBook();
                    break;
                case 4:
//                    libraryManager.removeBook();
                    break;
                case 5:
//                    libraryManager.sortBook();
                    break;
                case 6:
                    libraryManager.searchBook();
                    break;
                case 7:
                    System.out.println("Good bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    private static int pickOption() {
        int option = 0;
        try {
            System.out.print("What do you want to do?: ");
            Scanner scanner = new Scanner(System.in);
            option =  scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Please enter a number next time.");
        }
        return option;
    }
}

//    private static Book addNewBook() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter book title: ");
//        String title = scanner.nextLine();
//        System.out.print("Enter book author: ");
//        String author = scanner.nextLine();
//        System.out.print("Enter publisher: ");
//        String publisher = scanner.nextLine();
//        System.out.print("Enter published year: ");
//        int publishedYear = scanner.nextInt();
//        System.out.print("Is the book available: ");
//        boolean isAvailable = scanner.nextBoolean();
//        Book book = new Book(title, author, publisher, publishedYear, isAvailable);
//        return book;
//    }


