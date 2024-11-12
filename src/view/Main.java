package view;

import controller.LibraryController;
import model.Book;

import java.util.Scanner;

public class Main {
    private static LibraryController libraryController = new LibraryController();

    public static void main(String[] args) {
        while (true) {
//            System.out.println("=============================");
//            System.out.println("Welcome to library. Who are you?");
//            System.out.println("1. Manager");
//            System.out.println("2. User");
//            System.out.println("3. No one. I'm out.");
            System.out.println("=============================");
            System.out.println("Welcome to Library Manager");
            System.out.println("1. Show all books");
            System.out.println("2. Add a book");
            System.out.println("3. Edit a book");
            System.out.println("4. Remove a book");
            System.out.println("5. Sort books by title");
            System.out.println("6. Search for book(s)");
            System.out.println("7. Exit");

            switch (pickOption()) {
                case 1:
                    libraryController.displayAllBooks();
                    break;
                case 2:
                    libraryController.addBook(); // comment this to use sample data
//                    libraryController.addSampleBook();
                    break;
                case 3:
                    libraryController.editBook();
                    break;
                case 4:
                    libraryController.removeBook();
                    break;
                case 5:
                    libraryController.sortBook();
                    break;
                case 6:
                    libraryController.searchBook();
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
            System.out.println("Please enter a number.");
        }
        return option;
    }
// Manager mode
//    private static void managerMode() {
//        while (true) {
//            System.out.println("=============================");
//            System.out.println("Welcome back. What do you want to do?");
//            System.out.println("1. Show all books");
//            System.out.println("2. Add a book");
//            System.out.println("3. Edit a book");
//            System.out.println("4. Remove a book");
//            System.out.println("5. Sort books by title");
//            System.out.println("6. Search for book(s)");
//            System.out.println("7. Back to Main Menu");
//
//            switch (pickOption()) {
//                case 1:
//                    libraryController.displayAllBooks();
//                    break;
//                case 2:
//                    libraryController.addSampleBook(); // or libraryController.addBook();
//                    break;
//                case 3:
//                    libraryController.editBook();
//                    break;
//                case 4:
//                    libraryController.removeBook();
//                    break;
//                case 5:
//                    libraryController.sortBook();
//                    break;
//                case 6:
//                    libraryController.searchBook();
//                    break;
//                case 7:
//                    return; // Go back to the main menu
//                default:
//                    System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }

// User mode
//    private static void userMode() {
//        while (true) {
//            System.out.println("=============================");
//            System.out.println("Hi there. What do you want to do?");
//            System.out.println("1. Show all current books");
//            System.out.println("2. Borrow a book");
//            System.out.println("3. Sort books by title");
//            System.out.println("4. Sort books by year");
//            System.out.println("5. Back to Main Menu");
//
//            switch (pickOption()) {
//                case 1:
//                    libraryController.displayAllBooks();
//                    break;
//                case 2:
//                    borrowBook();
//                    break;
//                case 3:
//                    libraryController.sortBook(); // Assuming it sorts by title
//                    break;
//                case 4:
//                    // Implement sort by year if needed
//                    System.out.println("Sorting books by year (not implemented yet).");
//                    break;
//                case 5:
//                    return; // Go back to the main menu
//                default:
//                    System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }


}


