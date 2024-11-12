package view;

import controller.LibraryController;
import model.Book;
import java.util.Scanner;

public class Main {
    private static final LibraryController libraryController = new LibraryController();
    public static void main(String[] args) {
        while (true) {
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
}


