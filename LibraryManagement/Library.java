import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Library {
    private List<Book> books;
    private static final String FILE_NAME = "library_data.ser";

    public Library() {
        this.books = new ArrayList<>();
        loadBooksFromFile();
    }

    // Load books from file (Persistence)
    private void loadBooksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            books = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    // Save books to file
    private void saveBooksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    // Add a new book to the library
    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        saveBooksToFile();
    }

    // Borrow a book
    public void borrowBook(String title) {
        books.stream()
             .filter(book -> book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed())
             .findFirst()
             .ifPresentOrElse(book -> {
                 book.borrowBook();
                 saveBooksToFile();
                 System.out.println("You borrowed: " + book.getTitle());
             }, () -> System.out.println("Book not available or already borrowed."));
    }

    // Return a book
    public void returnBook(String title) {
        books.stream()
             .filter(book -> book.getTitle().equalsIgnoreCase(title) && book.isBorrowed())
             .findFirst()
             .ifPresentOrElse(book -> {
                 book.returnBook();
                 saveBooksToFile();
                 System.out.println("You returned: " + book.getTitle());
             }, () -> System.out.println("Book not found or not borrowed."));
    }

    // Display available books using Streams API
    public void displayAvailableBooks() {
        List<Book> availableBooks = books.stream()
                .filter(book -> !book.isBorrowed())
                .collect(Collectors.toList());

        if (availableBooks.isEmpty()) {
            System.out.println("No books available.");
        } else {
            availableBooks.forEach(System.out::println);
        }
    }
}
