package controller;


import controller.sortBook.BookSorter;
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
    public void searchBook() {
        BookSearcher.searchBook(books);
    }

    /*==============  SORT  ==============*/
    public void sortBook() {
        BookSorter.sortBook(books);
    }
}
