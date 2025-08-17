import java.util.ArrayList;
import java.util.Scanner;

// Model for Book
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isBorrowed() { return isBorrowed; }
    public void setBorrowed(boolean borrowed) { isBorrowed = borrowed; }

    @Override
    public String toString() {
        return id + ": " + title + " by " + author +
               (isBorrowed ? " [Borrowed]" : " [Available]");
    }
}

// Library Management System
public class LibraryManagement {
    private ArrayList<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n--- Library Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> listBooks();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();
        int id = books.size() + 1;
        books.add(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    private void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void borrowBook() {
        System.out.print("Enter Book ID to borrow: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isBorrowed()) {
            System.out.println("Book is already borrowed.");
        } else {
            book.setBorrowed(true);
            System.out.println("Book borrowed successfully.");
        }
    }

    private void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isBorrowed()) {
            System.out.println("Book is not borrowed.");
        } else {
            book.setBorrowed(false);
            System.out.println("Book returned successfully.");
        }
    }

    private Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public static void main(String[] args) {
        new LibraryManagement().start();
    }
}
