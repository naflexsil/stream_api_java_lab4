package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import lombok.Data;

@Data
class Visitor {
    private String name;
    private String surname;
    private String phone;
    private boolean subscribed;
    private List<Book> favoriteBooks;
}

@Data
class Book {
    private String name;
    private String author;
    private int publishingYear;
    private String isbn;
    private String publisher;
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("type 1 for count visitor or 2 for list unique books or 3 for:");
        String choice = scanner.nextLine();

        Gson gson = new Gson();
        Type visitorListType = new TypeToken<List<Visitor>>() {}.getType();

        try (FileReader reader = new FileReader("books.json")) {
            List<Visitor> visitors = gson.fromJson(reader, visitorListType);

            if ("1".equals(choice)) {
                displayVisitors(visitors);
            } else if ("2".equals(choice)) {
                displayUniqueFavoriteBooks(visitors);
            } else if ("3".equals(choice)) {
                displayBooksSortedByYear(visitors);
            } else {
                System.out.println("error choice");
            }
        } catch (IOException e) {
            System.out.println("error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void displayVisitors(List<Visitor> visitors) {
        System.out.println("Count visitor: " + visitors.size());
        System.out.println("List visitor:");

        for (Visitor visitor : visitors) {
            System.out.println(visitor.getName() + " " + visitor.getSurname());
            System.out.println("Phone number: " + visitor.getPhone());
            System.out.println("Favorite books:");
            for (Book book : visitor.getFavoriteBooks()) {
                System.out.println(" - " + book.getName() + " (author: " + book.getAuthor() + ")");
            }
            System.out.println();
        }
    }

    private static void displayUniqueFavoriteBooks(List<Visitor> visitors) {
        Set<Book> uniqueBooks = new HashSet<>();
        for (Visitor visitor : visitors) {
            uniqueBooks.addAll(visitor.getFavoriteBooks());
        }

        System.out.println("Count unique favorite books: " + uniqueBooks.size());
        System.out.println("List unique books:");
        for (Book book : uniqueBooks) {
            System.out.println(" - " + book.getName() + " (author: " + book.getAuthor() + ")");
        }
    }

    private static void displayBooksSortedByYear(List<Visitor> visitors) {
        List<Book> allBooks = new ArrayList<>();
        for (Visitor visitor : visitors) {
            allBooks.addAll(visitor.getFavoriteBooks());
        }

        allBooks.sort(Comparator.comparingInt(Book::getPublishingYear));

        System.out.println("List of books sorted by year of publication:");
        for (Book book : allBooks) {
            System.out.println(book.getPublishingYear() + " - " + book.getName() + " (author: " + book.getAuthor() + ")");
        }
    }
}